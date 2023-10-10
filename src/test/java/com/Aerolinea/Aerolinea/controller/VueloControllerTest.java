package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Vuelo;
import com.Aerolinea.Aerolinea.model.dto.VueloDto;
import com.Aerolinea.Aerolinea.service.VueloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@WebMvcTest(VueloController.class)//para ingresar al controlador especifico
class VueloControllerTest {

    private VueloController vueloController;
    private VueloService vueloService;

    @BeforeEach
    public void setUp() {
        vueloService = mock(VueloService.class);
        vueloController = new VueloController(vueloService);
    }

    @Test
    public void testAddVuelo() {

        VueloDto vueloDto = new VueloDto();
        vueloDto.setId(1L);
        vueloDto.setNombre("Vuelo de Prueba");


        Vuelo vuelo = Vuelo.from(vueloDto);

        when(vueloService.addVuelo(any(Vuelo.class))).thenReturn(vuelo);


        ResponseEntity<VueloDto> responseEntity = vueloController.addVuelo(vueloDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(vueloDto, responseEntity.getBody());
    }
}

