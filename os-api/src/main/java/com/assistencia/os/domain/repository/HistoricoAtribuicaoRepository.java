package com.assistencia.os.domain.repository;

import com.assistencia.os.domain.model.HistoricoAtribuicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoricoAtribuicaoRepository extends JpaRepository<HistoricoAtribuicao, UUID> {
    // Para auditar todas as trocas de técnico de uma OS específica
    List<HistoricoAtribuicao> findByOrdemServicoIdOrderByDataAlteracaoDesc(UUID osId);
}
