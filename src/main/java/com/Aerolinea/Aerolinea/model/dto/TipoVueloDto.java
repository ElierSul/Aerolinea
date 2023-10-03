package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.TipoVuelo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TipoVueloDto {

    private Long id;
    private boolean privado;
    private boolean publico;
    private List<VueloDto> vuelosDto = new ArrayList<>();

    public static TipoVueloDto from(TipoVuelo tipoVuelo){
        TipoVueloDto tipoVueloDto = new TipoVueloDto();
        tipoVueloDto.setId(tipoVuelo.getId());
        tipoVueloDto.setPrivado(tipoVuelo.isPrivado());
        tipoVueloDto.setPublico(tipoVuelo.isPublico());
        tipoVueloDto.setVuelosDto(tipoVuelo.getVuelos().stream().map(VueloDto::from).collect(Collectors.toList()));
        return tipoVueloDto;
    }
}
