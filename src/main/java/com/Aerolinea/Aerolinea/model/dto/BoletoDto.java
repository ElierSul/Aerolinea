package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.Boleto;
import lombok.Data;

import java.util.Objects;

@Data
public class BoletoDto {

    private Long id;
    private String serialNumber;
    private double pago;
    private PlainMedioPagoDto plainMedioPagoDto;
    private PlainUsuarioDto plainUsuarioDto;

    public static BoletoDto from(Boleto boleto){
        BoletoDto boletoDto = new BoletoDto();
        boletoDto.setId(boleto.getId());
        boletoDto.setSerialNumber(boleto.getSerialNumber());
        boletoDto.setPago(boleto.getPago());

        if(Objects.nonNull(boleto.getMedioPago())){
            boletoDto.setPlainMedioPagoDto(PlainMedioPagoDto.from(boleto.getMedioPago()));
        }

        if(Objects.nonNull(boleto.getUsuario())){
            boletoDto.setPlainUsuarioDto(PlainUsuarioDto.from(boleto.getUsuario()));
        }
        return boletoDto;
    }

}