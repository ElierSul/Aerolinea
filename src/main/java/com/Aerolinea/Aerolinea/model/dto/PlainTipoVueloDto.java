package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.TipoVuelo;
import lombok.Data;

@Data
public class PlainTipoVueloDto {
    private Long id;
    private String nombre;

    public static PlainTipoVueloDto from(TipoVuelo tipoVuelo){
        PlainTipoVueloDto plainTipoVueloDto = new PlainTipoVueloDto();
        plainTipoVueloDto.setId(tipoVuelo.getId());
        plainTipoVueloDto.setNombre(tipoVuelo.getNombre());
        return plainTipoVueloDto;
    }
}
