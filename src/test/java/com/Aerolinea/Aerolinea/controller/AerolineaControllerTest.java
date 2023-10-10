package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Aerolinea;
import com.Aerolinea.Aerolinea.model.dto.AerolineaDto;
import com.Aerolinea.Aerolinea.service.AerolineaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@WebMvcTest(AerolineaController.class)//para ingresar al controlador especifico
class AerolineaControllerTest {
    @Mock
    private AerolineaService aerolineaService;

    @InjectMocks
    private AerolineaController aerolineaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddAerolinea() {
        // Crear un objeto AerolineaDto para simular la solicitud del cliente
        AerolineaDto aerolineaDto = new AerolineaDto();
        aerolineaDto.setId(1L);
        aerolineaDto.setNombre("Aerolinea de Prueba");


        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setId(1L);
        aerolinea.setNombre("Aerolinea de Prueba");


        when(aerolineaService.addAerolinea(any(Aerolinea.class))).thenReturn(aerolinea);

        // Llamar al método del controlador
        ResponseEntity<AerolineaDto> response = aerolineaController.addAerolinea(aerolineaDto);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(aerolineaDto, response.getBody());
    }


}





