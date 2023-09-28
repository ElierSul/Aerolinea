package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.Usuario;
import lombok.Data;

@Data
public class PlainUsuarioDto {

    private Long id;
    private String documento;
    private String nombre;
    private String apellido;
    private String celular;
    private String email;
    private boolean equipaje;

    public static PlainUsuarioDto from(Usuario usuario){
        PlainUsuarioDto plainUsuarioDto = new PlainUsuarioDto();
        plainUsuarioDto.setId(usuario.getId());
        plainUsuarioDto.setDocumento(usuario.getDocumento());
        plainUsuarioDto.setNombre(usuario.getNombre());
        plainUsuarioDto.setApellido(usuario.getApellido());
        plainUsuarioDto.setCelular(usuario.getCelular());
        plainUsuarioDto.setEmail(usuario.getEmail());
        plainUsuarioDto.setEquipaje(usuario.isEquipaje());
        return plainUsuarioDto;
    }
}
