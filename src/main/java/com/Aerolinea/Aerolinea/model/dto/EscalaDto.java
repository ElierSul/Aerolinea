package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.Escala;
import lombok.Data;

import java.util.Objects;

@Data
public class EscalaDto {

    private Long id;
    private String serialNumber;
    private PlainTrasladoDto plainTrasladoDto;

    public static EscalaDto from(Escala escala){
        EscalaDto escalaDto = new EscalaDto();
        escalaDto.setId(escala.getId());
        escalaDto.setSerialNumber(escala.getSerialNumber());

        if (Objects.nonNull(escala.getTraslado())) {
            escalaDto.setPlainTrasladoDto(PlainTrasladoDto.from(escala.getTraslado()));
        }
        return escalaDto;
    }
}
