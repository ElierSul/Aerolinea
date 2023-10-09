package com.Aerolinea.Aerolinea.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.Aerolinea.Aerolinea.model.MedioPago;
import com.Aerolinea.Aerolinea.model.dto.MedioPagoDto;
import com.Aerolinea.Aerolinea.service.MedioPagoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class MedioPagoControllerTest {
    private MedioPagoController medioPagoController;
    private MedioPagoService medioPagoService;

    @BeforeEach
    public void setUp() {
        medioPagoService = mock(MedioPagoService.class);
        medioPagoController = new MedioPagoController(medioPagoService);
    }

    @Test
    public void testAddMedioPago() {
        // Datos de ejemplo para MedioPagoDto
        MedioPagoDto medioPagoDto = new MedioPagoDto();
        medioPagoDto.setId(1L);
        medioPagoDto.setNombre("Medio de Pago de Prueba");

        // Configurar el comportamiento del servicio al agregar un medio de pago
        when(medioPagoService.addMedioPago(any(MedioPago.class))).thenReturn(MedioPago.from(medioPagoDto));

        // Llamar al método del controlador
        ResponseEntity<MedioPagoDto> responseEntity = medioPagoController.addMedioPago(medioPagoDto);

        // Verificar que la respuesta sea exitosa (código 200) y que el medio de pago retornado sea el mismo que el creado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(medioPagoDto, responseEntity.getBody());
    }

    @Test
    public void testGetMediosPago() {
        // Datos de ejemplo para una lista de medios de pago
        List<MedioPago> mediosPago = new ArrayList<>();
        mediosPago.add(new MedioPago(1L, "Medio de Pago 1"));
        mediosPago.add(new MedioPago(2L, "Medio de Pago 2"));

        // Configurar el comportamiento del servicio al obtener todos los medios de pago
        when(medioPagoService.getMediosPago()).thenReturn(mediosPago);

        // Llamar al método del controlador
        ResponseEntity<List<MedioPagoDto>> responseEntity = medioPagoController.getMediosPago();

        // Verificar que la respuesta sea exitosa (código 200) y que la lista de medios de pago retornado sea el mismo que el creado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mediosPago.size(), responseEntity.getBody().size());
    }


}
