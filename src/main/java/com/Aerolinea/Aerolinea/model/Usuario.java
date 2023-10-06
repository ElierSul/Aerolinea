package com.Aerolinea.Aerolinea.model;

import com.Aerolinea.Aerolinea.model.dto.UsuarioDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "Usuario.findByEmail", query = "select u from Usuario u where u.email=:email")
@Entity
@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documento;

    private String nombre;

    private String apellido;

    private String celular;

    private String email;

    private String password;

    private String status;

    private String role;

    private boolean equipaje;



    @OneToMany(cascade = CascadeType.ALL)
    private List<Boleto> boletos = new ArrayList<>();

    public void addBoleto(Boleto boleto){
        boletos.add(boleto);
    }

    public void removeBoleto(Boleto boleto){
        boletos.remove(boleto);
    }

    public static Usuario from(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();
        usuario.setDocumento(usuarioDto.getDocumento());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setCelular(usuarioDto.getCelular());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setEquipaje(usuario.isEquipaje());
        usuario.setPassword(usuarioDto.getPassword());
        return usuario;
    }
}
