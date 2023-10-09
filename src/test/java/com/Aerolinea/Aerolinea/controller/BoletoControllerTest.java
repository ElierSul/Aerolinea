package com.Aerolinea.Aerolinea.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.dto.BoletoDto;
import com.Aerolinea.Aerolinea.service.BoletoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class BoletoControllerTest {
    private BoletoController boletoController;
    private BoletoService boletoService;

    @BeforeEach
    public void setUp() {
        boletoService = mock(BoletoService.class);
        boletoController = new BoletoController(boletoService);
    }

    @Test
    public void testAddBoleto() {
        // Datos de ejemplo para BoletoDto
        BoletoDto boletoDto = new BoletoDto();
        boletoDto.setId(1L);
        boletoDto.setNumero("123456");

        // Configurar el comportamiento del servicio al agregar un boleto
        when(boletoService.addBoleto(any(Boleto.class))).thenReturn(Boleto.from(boletoDto));

        // Llamar al método del controlador
        ResponseEntity<BoletoDto> responseEntity = boletoController.addBoleto(boletoDto);

        // Verificar que la respuesta sea exitosa (código 200) y que el boleto retornado sea el mismo que el creado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(boletoDto, responseEntity.getBody());
    }

    @Test
    public void testGetBoletos() {
        // Datos de ejemplo para una lista de boletos
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto(1L, "123456"));
        boletos.add(new Boleto(2L, "789012"));

        // Configurar el comportamiento del servicio al obtener todos los boletos
        when(boletoService.getBoletos()).thenReturn(boletos);

        // Llamar al método del controlador
        ResponseEntity<List<BoletoDto>> responseEntity = boletoController.getBoletos();

        // Verificar que la respuesta sea exitosa (código 200) y que la lista de boletos retornado sea el mismo que el creado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(boletos.size(), responseEntity.getBody().size());
    }


}
