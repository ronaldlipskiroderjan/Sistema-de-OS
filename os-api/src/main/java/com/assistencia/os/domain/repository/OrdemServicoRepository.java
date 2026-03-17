package com.assistencia.os.domain.repository;

import com.assistencia.os.domain.model.OrdemServico;
import com.assistencia.os.domain.model.StatusOrdemServico;
import com.assistencia.os.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, UUID> {
    // Para o técnico ver apenas as OS dele que ainda não foram concluídas
    List<OrdemServico> findByTecnicoAndStatus(Usuario tecnico, StatusOrdemServico status);
    // Para o escritório filtrar por status
    List<OrdemServico> findByStatus(StatusOrdemServico status);
    // Para buscar OS de um cliente específico
    List<OrdemServico> findByClienteId(UUID clienteId);
}
