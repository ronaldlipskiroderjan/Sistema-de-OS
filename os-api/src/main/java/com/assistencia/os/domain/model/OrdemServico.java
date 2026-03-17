package com.assistencia.os.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name= "ordens_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tecnico_id", nullable = false)
    private Usuario tecnico;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(columnDefinition = "TEXT")
    private

    private BigDecimal valorTotal;

    @Lob
    @Column(columnDefintion = "TEXT")
    private String assinaturaCliente;

    private OffsetDateTime dataAbertura;
    private OffsetDateTime dataFechamento;

    @PrePersist
    private void aoCriar() {
        this.dataAbertura = OffsetDateTime.now();
        this.status = StatusOrdemServico.ABERTA;
    }
}
