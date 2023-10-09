package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.Escala;
import com.Aerolinea.Aerolinea.model.Vuelo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VueloServiceTest {

    @Test
    void testAddBoleto() {
        Vuelo vuelo = new Vuelo();
        Boleto boleto = new Boleto();

        vuelo.addBoleto(boleto);

        assertEquals(1, vuelo.getBoletos().size());
        assertEquals(boleto, vuelo.getBoletos().get(0));
    }

    @Test
    void testAddEscala() {
        Vuelo vuelo = new Vuelo();
        Escala escala = new Escala();

        vuelo.addEscala(escala);

        assertEquals(1, vuelo.getEscalas().size());
        assertEquals(escala, vuelo.getEscalas().get(0));
    }
}
