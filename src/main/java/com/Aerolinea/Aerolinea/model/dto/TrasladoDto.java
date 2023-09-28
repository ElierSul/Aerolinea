package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.Traslado;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TrasladoDto {

    private Long id;
    private String pais;
    private String estado;
    private String ciudad;
    private String aeropuerto;
    private Date fechaOrigen;
    private Date fechaDestino;
    private boolean soloIda;
    private boolean idaVuelta;
    private List<EscalaDto> escalasDto = new ArrayList<>();

    public static TrasladoDto from(Traslado traslado){
        TrasladoDto trasladoDto = new TrasladoDto();
        trasladoDto.setId(traslado.getId());
        trasladoDto.setPais(traslado.getPais());
        trasladoDto.setEstado(traslado.getEstado());
        trasladoDto.setCiudad(traslado.getCiudad());
        trasladoDto.setAeropuerto(traslado.getAeropuerto());
        trasladoDto.setSoloIda(traslado.isSoloIda());
        trasladoDto.setIdaVuelta(traslado.isIdaVuelta());
        trasladoDto.setEscalasDto(traslado.getEscalas().stream().map(EscalaDto::from).collect(Collectors.toList()));
        return trasladoDto;
    }
}
