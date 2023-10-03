package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.Boleto;
import lombok.Data;

import java.util.Objects;

@Data
public class BoletoDto {

    private Long id;
    private int lugar;
    private PlainMedioPagoDto MedioPago;
    private PlainUsuarioDto Usuario;
    private PlainVueloDto vuelos;

    public static BoletoDto from(Boleto boleto){
        BoletoDto boletoDto = new BoletoDto();
        boletoDto.setId(boleto.getId());
        boletoDto.setLugar(boleto.getLugar());

        if(Objects.nonNull(boleto.getMedioPago())){
            boletoDto.setMedioPago(PlainMedioPagoDto.from(boleto.getMedioPago()));
        }

        if(Objects.nonNull(boleto.getUsuario())){
            boletoDto.setUsuario(PlainUsuarioDto.from(boleto.getUsuario()));
        }

        if(Objects.nonNull(boleto.getVuelo())){
            boletoDto.setVuelos(PlainVueloDto.from(boleto.getVuelo()));
        }
        return boletoDto;
    }

}
