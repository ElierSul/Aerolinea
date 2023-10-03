package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.Escala;
import lombok.Data;
import java.util.Date;
import java.util.Objects;

@Data
public class EscalaDto {

    private Long id;
    private String paisOrigen;
    private String paisDestino;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String aeropuertoOrigen;
    private String aeropuertoDestino;
    private Date fechaOrigen;
    private Date fechaDestino;
    private PlainVueloDto plainVueloDto;

    public static EscalaDto from(Escala escala){
        EscalaDto escalaDto = new EscalaDto();
        escalaDto.setId(escala.getId());
        escalaDto.setPaisOrigen(escala.getPaisOrigen());
        escalaDto.setPaisDestino(escala.getPaisDestino());
        escalaDto.setCiudadOrigen(escala.getCiudadOrigen());
        escalaDto.setCiudadDestino(escala.getCiudadDestino());
        escalaDto.setAeropuertoOrigen(escala.getAeropuertoOrigen());
        escalaDto.setAeropuertoDestino(escala.getAeropuertoDestino());
        escalaDto.setFechaOrigen(escala.getFechaOrigen());
        escalaDto.setFechaDestino(escala.getFechaDestino());

        if(Objects.nonNull(escala.getVuelo())){
            escalaDto.setPlainVueloDto(PlainVueloDto.from(escala.getVuelo()));
        }
        return escalaDto;
    }
}
