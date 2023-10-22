package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.Vuelo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class VueloDto {

    private Long id;
    private int lugaresDisponibles;
    private double costo;
    private String codigo;
    private PlainAerolineaDto aerolineas;
    private PlainTipoVueloDto tipoVuelo;
    private List<EscalaDto> escalasDto = new ArrayList<>();
    private List<BoletoDto> boletosDto = new ArrayList<>();


    public static VueloDto from(Vuelo vuelo){
        VueloDto vueloDto = new VueloDto();
        vueloDto.setId(vuelo.getId());
        vueloDto.setLugaresDisponibles(vuelo.getLugaresDisponibles());
        vueloDto.setCosto(vuelo.getCosto());
        vueloDto.setCodigo(vuelo.getCodigo());
        vueloDto.setEscalasDto(vuelo.getEscalas().stream().map(EscalaDto::from).collect(Collectors.toList()));
        vueloDto.setBoletosDto(vuelo.getBoletos().stream().map(BoletoDto::from).collect(Collectors.toList()));

        if (Objects.nonNull(vuelo.getAerolinea())) {
            vueloDto.setAerolineas(PlainAerolineaDto.from(vuelo.getAerolinea()));
        }

        if (Objects.nonNull(vuelo.getTipoVuelo())) {
            vueloDto.setTipoVuelo(PlainTipoVueloDto.from(vuelo.getTipoVuelo()));
        }

        return vueloDto;
    }

    public void setNombre(String vueloDePrueba) {
    }
}
