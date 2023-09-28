package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.MedioPago;
import lombok.Data;

@Data
public class PlainMedioPagoDto {
    private Long id;
    private boolean pse;
    private boolean tarjetaCredito;
    private boolean tarjetaDebito;

    public static PlainMedioPagoDto from(MedioPago medioPago){
        PlainMedioPagoDto plainMedioPagoDto = new PlainMedioPagoDto();
        plainMedioPagoDto.setId(medioPago.getId());
        plainMedioPagoDto.setPse(medioPago.isPse());
        plainMedioPagoDto.setTarjetaCredito(medioPago.isTarjetaCredito());
        plainMedioPagoDto.setTarjetaDebito(medioPago.isTarjetaDebito());
        return plainMedioPagoDto;
    }
}
