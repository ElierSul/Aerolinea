package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.Aerolinea;
import lombok.Data;

@Data
public class PlainAerolineaDto {

    private Long id;
    private String nombre;
    private String prefijo;
    private int consecutivo;

    public static PlainAerolineaDto from(Aerolinea aerolinea){
        PlainAerolineaDto plainAerolineaDto = new PlainAerolineaDto();
        plainAerolineaDto.setId(aerolinea.getId());
        plainAerolineaDto.setNombre(aerolinea.getNombre());
        plainAerolineaDto.setPrefijo(aerolinea.getPrefijo());
        plainAerolineaDto.setConsecutivo(aerolinea.getConsecutivo());
        return plainAerolineaDto;
    }
}
