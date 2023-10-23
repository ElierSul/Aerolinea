package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.MedioPago;
import lombok.Data;

@Data
public class PlainMedioPagoDto {
    private Long id;
    private String nombre;

    public static PlainMedioPagoDto from(MedioPago medioPago){
        PlainMedioPagoDto plainMedioPagoDto = new PlainMedioPagoDto();
        plainMedioPagoDto.setId(medioPago.getId());
        plainMedioPagoDto.setNombre(medioPago.getNombre());
        return plainMedioPagoDto;
    }
}
