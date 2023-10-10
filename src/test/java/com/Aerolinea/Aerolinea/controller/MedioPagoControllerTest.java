package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.MedioPago;
import com.Aerolinea.Aerolinea.model.dto.MedioPagoDto;
import com.Aerolinea.Aerolinea.service.MedioPagoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest(MedioPagoController.class)//para ingresar al controlador especifico
class MedioPagoControllerTest {

    private MedioPagoController medioPagoController;
    private MedioPagoService medioPagoService;

    @BeforeEach
    public void setUp() {
        medioPagoService = mock(MedioPagoService.class);
        medioPagoController = new MedioPagoController(medioPagoService);
    }

    @Test
    public void testAddMedioPago() {

        MedioPagoDto medioPagoDto = new MedioPagoDto();
        medioPagoDto.setId(1L);
        medioPagoDto.setNombre("Medio de Pago de Prueba");

        MedioPago medioPago = MedioPago.from(medioPagoDto);

        when(medioPagoService.addMedioPago(any(MedioPago.class))).thenReturn(medioPago);
        ResponseEntity<MedioPagoDto> responseEntity = medioPagoController.addMedioPago(medioPagoDto);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(medioPagoDto, responseEntity.getBody());
    }
}



