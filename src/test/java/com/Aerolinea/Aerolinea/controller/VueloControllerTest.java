package com.Aerolinea.Aerolinea.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.Aerolinea.Aerolinea.model.Vuelo;
import com.Aerolinea.Aerolinea.model.dto.VueloDto;
import com.Aerolinea.Aerolinea.service.VueloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class VueloControllerTest {
    private VueloController vueloController;
    private VueloService vueloService;

    @BeforeEach
    public void setUp() {
        vueloService = mock(VueloService.class);
        vueloController = new VueloController(vueloService);
    }

    @Test
    public void testAddVuelo() {
        // Datos de ejemplo para el DTO de vuelo
        VueloDto vueloDto = new VueloDto();
        vueloDto.setId(1L);
        vueloDto.setNombre("Vuelo de prueba");

        // Configurar el comportamiento del servicio al agregar un vuelo
        when(vueloService.addVuelo(any())).thenReturn(new Vuelo(1L, "Vuelo de prueba"));

        // Llamar al método del controlador
        ResponseEntity<VueloDto> responseEntity = vueloController.addVuelo(vueloDto);

        // Verificar que la respuesta sea exitosa (código 200) y que el DTO retornado sea el mismo que el creado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(vueloDto, responseEntity.getBody());
    }

    @Test
    public void testGetVuelos() {
        // Datos de ejemplo para una lista de vuelos
        List<Vuelo> vuelos = new ArrayList<>();
        vuelos.add(new Vuelo(1L, "Vuelo 1"));
        vuelos.add(new Vuelo(2L, "Vuelo 2"));

        // Configurar el comportamiento del servicio al obtener todos los vuelos
        when(vueloService.getVuelos()).thenReturn(vuelos);

        // Llamar al método del controlador
        ResponseEntity<List<VueloDto>> responseEntity = vueloController.getVuelos();

        // Verificar que la respuesta sea exitosa (código 200) y que la lista de vuelos retornado sea el mismo que el creado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(vuelos.size(), responseEntity.getBody().size());
    }


}
