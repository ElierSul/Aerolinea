package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.Vuelo;
import lombok.Data;

@Data
public class PlainVueloDto {
    private Long id;
    private int lugaresDispoibles;
    private double costo;
    private String codigo;

    public static PlainVueloDto from(Vuelo vuelo){
        PlainVueloDto plainVueloDto = new PlainVueloDto();
        plainVueloDto.setId(vuelo.getId());
        plainVueloDto.setLugaresDispoibles(vuelo.getLugaresDisponibles());
        plainVueloDto.setCosto(vuelo.getCosto());
        plainVueloDto.setCodigo(vuelo.getCodigo());
        return plainVueloDto;
    }
}
