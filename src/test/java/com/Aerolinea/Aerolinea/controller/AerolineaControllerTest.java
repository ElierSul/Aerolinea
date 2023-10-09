package com.Aerolinea.Aerolinea.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.Aerolinea.Aerolinea.model.Aerolinea;
import com.Aerolinea.Aerolinea.model.dto.AerolineaDto;
import com.Aerolinea.Aerolinea.service.AerolineaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class AerolineaControllerTest {
    private AerolineaController aerolineaController;
    private AerolineaService aerolineaService;

    @BeforeEach
    public void setUp() {
        aerolineaService = mock(AerolineaService.class);
        aerolineaController = new AerolineaController(aerolineaService);
    }

    @Test
    public void testAddAerolinea() {
        // Datos de ejemplo para AerolineaDto
        AerolineaDto aerolineaDto = new AerolineaDto();
        aerolineaDto.setId(1L);
        aerolineaDto.setNombre("Aerolinea de Prueba");

        // Configurar el comportamiento del servicio al agregar una aerolínea
        when(aerolineaService.addAerolinea(any(Aerolinea.class))).thenReturn(Aerolinea.from(aerolineaDto));

        // Llamar al método del controlador
        ResponseEntity<AerolineaDto> responseEntity = aerolineaController.addAerolinea(aerolineaDto);

        // Verificar que la respuesta sea exitosa (código 200) y que la aerolínea retornado sea el mismo que el creado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(aerolineaDto, responseEntity.getBody());
    }

    @Test
    public void testGetAerolineas() {
        // Datos de ejemplo para una lista de aerolíneas
        List<Aerolinea> aerolineas = new ArrayList<>();
        aerolineas.add(new Aerolinea(1L, "Aerolinea 1"));
        aerolineas.add(new Aerolinea(2L, "Aerolinea 2"));

        // Configurar el comportamiento del servicio al obtener todas las aerolíneas
        when(aerolineaService.getAerolineas()).thenReturn(aerolineas);

        // Llamar al método del controlador
        ResponseEntity<List<AerolineaDto>> responseEntity = aerolineaController.getAerolineas();

        // Verificar que la respuesta sea exitosa (código 200) y que la lista de aerolíneas retornado sea el mismo que el creado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(aerolineas.size(), responseEntity.getBody().size());
    }
}
