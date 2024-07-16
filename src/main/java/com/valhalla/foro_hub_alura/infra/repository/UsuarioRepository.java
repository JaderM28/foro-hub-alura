package com.valhalla.foro_hub_alura.infra.repository;

import com.valhalla.foro_hub_alura.domain.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByUsername( String username);

}
