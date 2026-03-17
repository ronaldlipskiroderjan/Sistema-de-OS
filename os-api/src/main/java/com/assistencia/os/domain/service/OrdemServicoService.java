package com.assistencia.os.domain.service;

import com.assistencia.os.domain.model.HistoricoAtribuicao;
import com.assistencia.os.domain.model.OrdemServico;
import com.assistencia.os.domain.model.StatusOrdemServico;
import com.assistencia.os.domain.model.Usuario;
import com.assistencia.os.domain.repository.HistoricoAtribuicaoRepository;
import com.assistencia.os.domain.repository.OrdemServicoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdemServicoService {

    private final OrdemServicoRepository osRepository;
    private final HistoricoAtribuicaoRepository historicoRepository;

    @Transactional
    public OrdemServico criar(OrdemServico os) {
        os.setStatus(StatusOrdemServico.ABERTA);
        os.setDataAbertura(OffsetDateTime.now());

        OrdemServico osSalva = osRepository.save(os);

        registrarHistorico(osSalva, osSalva.getTecnico(), null);

        return osSalva;
    }

    @Transactional
    public void reatribuir(UUID osId, Usuario novoTecnico, Usuario atendenteQueAlterou) {
        OrdemServico os = osRepository.findById(osId)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada"));

        if (!os.getTecnico().equals(novoTecnico)) {
            os.setTecnico(novoTecnico);
            osRepository.save(os);

            registrarHistorico(os, novoTecnico, atendenteQueAlterou);
        }
    }

    @Transactional
    public void finalizar(UUID osId, String laudo, String assinaturaBase64) {
        OrdemServico os = osRepository.findById(osId)
                .orElseThrow(() -> new RuntimeException("OS não encontrada"));

        if (assinaturaBase64 == null || assinaturaBase64.isEmpty()) {
            throw new RuntimeException("A assinatura do cliente é obrigatória para finalizar.");
        }


        os.setLaudoTecnico(laudo);
        os.setAssinaturaCliente(assinaturaBase64);
        os.setStatus(StatusOrdemServico.CONCLUIDA);
        os.setDataFechamento(OffsetDateTime.now());

        osRepository.save(os);
    }

    private void registrarHistorico(OrdemServico os, Usuario tecnico, Usuario atendente){
        HistoricoAtribuicao historico = new HistoricoAtribuicao();
        historico.setOrdemServico(os);
        historico.setTecnico(tecnico);
        historico.setAtribuidoPor(atendente);
        historico.setDataAlteracao(OffsetDateTime.now());

        historicoRepository.save(historico);
    }
}