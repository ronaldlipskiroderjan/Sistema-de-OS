package com.assistencia.os.domain.repository;

import com.assistencia.os.domain.model.PerfilUsuario;
import com.assistencia.os.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByLogin(String login);

    List<Usuario> findByPerfil(PerfilUsuario perfil);

}
