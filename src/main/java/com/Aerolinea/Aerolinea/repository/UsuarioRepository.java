package com.Aerolinea.Aerolinea.repository;

import com.Aerolinea.Aerolinea.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(@Param(("email")) String email);
}
