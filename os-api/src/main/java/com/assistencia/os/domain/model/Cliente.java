package com.assistencia.os.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String nome;

    private String telefone;

    @Column(columnDefinition = "TEXT")
    private String endereco;

    @Column(unique = true)
    private String cpfCnpj;
}
