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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(columnDefinition = "TEXT")
    private String laudoTecnico;

    private BigDecimal valorTotal;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String assinaturaCliente;

    private OffsetDateTime dataAbertura;
    private OffsetDateTime dataFechamento;

    @PrePersist
    private void aoCriar() {
        this.dataAbertura = OffsetDateTime.now();
        this.status = StatusOrdemServico.ABERTA;
    }
}