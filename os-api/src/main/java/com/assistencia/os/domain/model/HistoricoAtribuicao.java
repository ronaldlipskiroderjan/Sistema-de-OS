package com.assistencia.os.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
public class HistoricoAtribuicao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private OrdemServico ordemServico;

    @ManyToOne
    private Usuario tecnico;

    @ManyToOne
    private Usuario atribuidoPor;

    private OffsetDateTime dataAlteracao;

    @PrePersist
    private void aoGravar() {
        this.dataAlteracao = OffsetDateTime.now();
    }
}
