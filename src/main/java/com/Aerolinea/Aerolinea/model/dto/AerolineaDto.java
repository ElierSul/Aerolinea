package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.Aerolinea;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AerolineaDto {

    private Long id;
    private String nombre;
    private List<VueloDto> vueloDto = new ArrayList<>();

    public static AerolineaDto from(Aerolinea aerolinea) {
        AerolineaDto aerolineaDto = new AerolineaDto();
        aerolineaDto.setId(aerolinea.getId());
        aerolineaDto.setNombre(aerolinea.getNombre());
        aerolineaDto.setVueloDto(aerolinea.getVuelos().stream().map(VueloDto::from).collect(Collectors.toList()));
        return aerolineaDto;
    }
}
