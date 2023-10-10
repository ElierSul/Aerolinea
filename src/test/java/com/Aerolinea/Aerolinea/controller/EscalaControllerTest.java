package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Escala;
import com.Aerolinea.Aerolinea.model.dto.EscalaDto;
import com.Aerolinea.Aerolinea.service.EscalaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@WebMvcTest(EscalaController.class)//para ingresar al controlador especifico
class EscalaControllerTest {

    private EscalaController escalaController;
    private EscalaService escalaService;

    @BeforeEach
    public void setUp() {
        escalaService = mock(EscalaService.class);
        escalaController = new EscalaController(escalaService);
    }

    @Test
    public void testAddEscala() {

        EscalaDto escalaDto = new EscalaDto();
        escalaDto.setId(1L);
        escalaDto.setNombre("Escala de Prueba");
        Escala escala = Escala.from(escalaDto);
        when(escalaService.addEscala(any(Escala.class))).thenReturn(escala);
        ResponseEntity<EscalaDto> responseEntity = escalaController.addEscala(escalaDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(escalaDto, responseEntity.getBody());
    }
}


