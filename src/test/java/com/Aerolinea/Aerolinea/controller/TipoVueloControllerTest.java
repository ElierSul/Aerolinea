package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.TipoVuelo;
import com.Aerolinea.Aerolinea.model.dto.TipoVueloDto;
import com.Aerolinea.Aerolinea.service.TipoVueloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@WebMvcTest(TipoVueloController.class)//para ingresar al controlador especifico
class TipoVueloControllerTest {

    private TipoVueloController tipoVueloController;
    private TipoVueloService tipoVueloService;

    @BeforeEach
    public void setUp() {
        tipoVueloService = mock(TipoVueloService.class);
        tipoVueloController = new TipoVueloController(tipoVueloService);
    }

    @Test
    public void testAddTipoVuelo() {

        TipoVueloDto tipoVueloDto = new TipoVueloDto();
        tipoVueloDto.setId(1L);
        tipoVueloDto.setNombre("Tipo de Vuelo de Prueba");

        TipoVuelo tipoVuelo = TipoVuelo.from(tipoVueloDto);


        when(tipoVueloService.addTipoVuelo(any(TipoVuelo.class))).thenReturn(tipoVuelo);

        ResponseEntity<TipoVueloDto> responseEntity = tipoVueloController.addTipoVuelo(tipoVueloDto);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tipoVueloDto, responseEntity.getBody());
    }
}


