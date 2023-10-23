package com.Aerolinea.Aerolinea.model.dto;

import com.Aerolinea.Aerolinea.model.MedioPago;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MedioPagoDto {

    private Long id;
    private String nombre;
    private List<BoletoDto> boletosDto = new ArrayList<>();

    public static MedioPagoDto from(MedioPago medioPago){
        MedioPagoDto medioPagoDto = new MedioPagoDto();
        medioPagoDto.setId(medioPago.getId());
        medioPagoDto.setNombre(medioPago.getNombre());
        medioPagoDto.setBoletosDto(medioPago.getBoletos().stream().map(BoletoDto::from).collect(Collectors.toList()));
        return medioPagoDto;
    }

    public void setNombre(String medioDePagoDePrueba) {
    }
}
