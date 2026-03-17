package com.assistencia.os.api.controller;

import com.assistencia.os.domain.model.OrdemServico;
import com.assistencia.os.domain.model.Usuario;
import com.assistencia.os.domain.repository.OrdemServicoRepository;
import com.assistencia.os.domain.service.OrdemServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.assistencia.os.api.dto.FinalizacaoOSRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/ordens-servico") // Versionei como v1, boa prática de mercado
@RequiredArgsConstructor
public class OrdemServicoController {

    private final OrdemServicoService osService;
    private final OrdemServicoRepository osRepository;

    // Listar todas - Útil para o painel do Escritório
    @GetMapping
    public List<OrdemServico> listar() {
        return osRepository.findAll();
    }

    // Buscar uma específica
    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscar(@PathVariable UUID id) {
        return osRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar nova OS (Usado pelo Escritório)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico abrir(@RequestBody OrdemServico os) {
        return osService.criar(os);
    }

    // Reatribuir técnico (Ação do Escritório)
    @PatchMapping("/{id}/reatribuir")
    public void trocarTecnico(@PathVariable UUID id, @RequestBody Usuario novoTecnico) {
        // No futuro, pegaremos o 'atendenteQueAlterou' do token de login
        osService.reatribuir(id, novoTecnico, null);
    }

    // Finalizar OS (O que o técnico vai chamar pelo celular)
    @PutMapping("/{id}/finalizar")
    public void concluir(@PathVariable UUID id, @RequestBody FinalizacaoOSRequest request) {
        osService.finalizar(id, request.getLaudo(), request.getAssinaturaBase64());
    }
}