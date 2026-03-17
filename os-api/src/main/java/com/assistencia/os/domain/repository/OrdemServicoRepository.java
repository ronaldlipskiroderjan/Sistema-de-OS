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
    List<OrdemServico> findByTecnicoId(UUID tecnicoId);

    List<OrdemServico> findByStatus(StatusOrdemServico status);
}

