package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.dto.BoletoDto;
import com.Aerolinea.Aerolinea.service.BoletoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@WebMvcTest(BoletoController.class)//para ingresar al controlador especifico
class BoletoControllerTest {

    private BoletoController boletoController;
    private BoletoService boletoService;

    @BeforeEach
    public void setUp() {
        boletoService = mock(BoletoService.class);
        boletoController = new BoletoController(boletoService);
    }

    @Test
    public void testAddBoleto() {

        BoletoDto boletoDto = new BoletoDto();
        boletoDto.setId(1L);
        boletoDto.setNumero("123456");

        Boleto boleto = Boleto.from(boletoDto);


        when(boletoService.addBoleto(any(Boleto.class))).thenReturn(boleto);


        ResponseEntity<BoletoDto> responseEntity = boletoController.addBoleto(boletoDto);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(boletoDto, responseEntity.getBody());
    }
}




