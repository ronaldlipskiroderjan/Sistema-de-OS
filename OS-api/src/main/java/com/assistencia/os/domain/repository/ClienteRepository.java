package com.assistencia.os.domain.repository;

import com.assistencia.os.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    // Busca rápida por CPF ou CNPJ
    Optional<Cliente> findByCpfCnpj(String cpfCnpj);
}

