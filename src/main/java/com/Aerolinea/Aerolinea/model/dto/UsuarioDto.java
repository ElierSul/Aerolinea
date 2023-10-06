package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.Usuario;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UsuarioDto {

    private Long id;
    private String documento;
    private String nombre;
    private String apellido;
    private String celular;
    private String email;
    private String password;
    private boolean equipaje;
    private List<BoletoDto> boletosDto = new ArrayList<>();

    public static UsuarioDto from(Usuario usuario){
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setDocumento(usuario.getDocumento());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setApellido(usuario.getApellido());
        usuarioDto.setCelular(usuario.getCelular());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setPassword(usuarioDto.getPassword());
        usuarioDto.setEquipaje(usuarioDto.isEquipaje());
        usuarioDto.setBoletosDto(usuario.getBoletos().stream().map(BoletoDto::from).collect(Collectors.toList()));
        return usuarioDto;
    }
}
