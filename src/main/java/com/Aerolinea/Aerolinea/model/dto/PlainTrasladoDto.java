package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.Traslado;
import lombok.Data;

import java.util.Date;

@Data
public class PlainTrasladoDto {

    private Long id;
    private String pais;
    private String estado;
    private String ciudad;
    private String aeropuerto;
    private Date fechaOrigen;
    private Date fechaDestino;
    private boolean soloIda;
    private boolean idaVuelta;

    public static PlainTrasladoDto from(Traslado traslado){
        PlainTrasladoDto plainTrasladoDto = new PlainTrasladoDto();
        plainTrasladoDto.setId(traslado.getId());
        plainTrasladoDto.setPais(traslado.getPais());
        plainTrasladoDto.setEstado(traslado.getEstado());
        plainTrasladoDto.setCiudad(traslado.getCiudad());
        plainTrasladoDto.setAeropuerto(traslado.getAeropuerto());
        plainTrasladoDto.setFechaOrigen(traslado.getFechaOrigen());
        plainTrasladoDto.setFechaDestino(traslado.getFechaDestino());
        plainTrasladoDto.setSoloIda(traslado.isSoloIda());
        plainTrasladoDto.setIdaVuelta(traslado.isIdaVuelta());
        return plainTrasladoDto;
    }
}
