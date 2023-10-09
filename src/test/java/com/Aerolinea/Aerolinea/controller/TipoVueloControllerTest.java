package com.Aerolinea.Aerolinea.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.Aerolinea.Aerolinea.model.TipoVuelo;
import com.Aerolinea.Aerolinea.model.dto.TipoVueloDto;
import com.Aerolinea.Aerolinea.service.TipoVueloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class TipoVueloControllerTest {
    private TipoVueloController tipoVueloController;
    private TipoVueloService tipoVueloService;

    @BeforeEach
    public void setUp() {
        tipoVueloService = mock(TipoVueloService.class);
        tipoVueloController = new TipoVueloController(tipoVueloService);
    }

    @Test
    public void testAddTipoVuelo() {
        // Datos de ejemplo para TipoVueloDto
        TipoVueloDto tipoVueloDto = new TipoVueloDto();
        tipoVueloDto.setId(1L);
        tipoVueloDto.setNombre("Tipo de Vuelo de Prueba");

        // Configurar el comportamiento del servicio al agregar un tipo de vuelo
        when(tipoVueloService.addTipoVuelo(any(TipoVuelo.class))).thenReturn(TipoVuelo.from(tipoVueloDto));

        // Llamar al método del controlador
        ResponseEntity<TipoVueloDto> responseEntity = tipoVueloController.addTipoVuelo(tipoVueloDto);

        // Verificar que la respuesta sea exitosa (código 200) y que el tipo de vuelo retornado sea el mismo que el creado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tipoVueloDto, responseEntity.getBody());
    }

    @Test
    public void testGetTiposVuelo() {
        // Datos de ejemplo para una lista de tipos de vuelo
        List<TipoVuelo> tiposVuelo = new ArrayList<>();
        tiposVuelo.add(new TipoVuelo(1L, "Tipo de Vuelo 1"));
        tiposVuelo.add(new TipoVuelo(2L, "Tipo de Vuelo 2"));

        // Configurar el comportamiento del servicio al obtener todos los tipos de vuelo
        when(tipoVueloService.getTiposVuelo()).thenReturn(tiposVuelo);

        // Llamar al método del controlador
        ResponseEntity<List<TipoVueloDto>> responseEntity = tipoVueloController.getTiposVuelo();

        // Verificar que la respuesta sea exitosa (código 200) y que la lista de tipos de vuelo retornado sea el mismo que el creado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tiposVuelo.size(), responseEntity.getBody().size());
    }


}
