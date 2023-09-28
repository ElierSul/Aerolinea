package com.Aerolinea.Aerolinea.repository;

import com.Aerolinea.Aerolinea.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
