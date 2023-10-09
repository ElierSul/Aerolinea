package com.Aerolinea.Aerolinea.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.Aerolinea.Aerolinea.model.Escala;
import com.Aerolinea.Aerolinea.model.dto.EscalaDto;
import com.Aerolinea.Aerolinea.service.EscalaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class EscalaControllerTest {
    private EscalaController escalaController;
    private EscalaService escalaService;

    @BeforeEach
    public void setUp() {
        escalaService = mock(EscalaService.class);
        escalaController = new EscalaController(escalaService);
    }

    @Test
    public void testAddEscala() {
        // Datos de ejemplo para EscalaDto
        EscalaDto escalaDto = new EscalaDto();
        escalaDto.setId(1L);
        escalaDto.setNombre("Escala de Prueba");

        // Configurar el comportamiento del servicio al agregar una escala
        when(escalaService.addEscala(any(Escala.class))).thenReturn(Escala.from(escalaDto));

        // Llamar al método del controlador
        ResponseEntity<EscalaDto> responseEntity = escalaController.addEscala(escalaDto);

        // Verificar que la respuesta sea exitosa (código 200) y que la escala retornada sea el mismo que el creado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(escalaDto, responseEntity.getBody());
    }

    @Test
    public void testGetEscalas() {
        // Datos de ejemplo para una lista de escalas
        List<Escala> escalas = new ArrayList<>();
        escalas.add(new Escala(1L, "Escala 1"));
        escalas.add(new Escala(2L, "Escala 2"));

        // Configurar el comportamiento del servicio al obtener todas las escalas
        when(escalaService.getEscalas()).thenReturn(escalas);

        // Llamar al método del controlador
        ResponseEntity<List<EscalaDto>> responseEntity = escalaController.getEscalas();

        // Verificar que la respuesta sea exitosa (código 200) y que la lista de escalas retornada sea el mismo que el creado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(escalas.size(), responseEntity.getBody().size());
    }
}
