package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.Escala;
import com.Aerolinea.Aerolinea.model.exception.EscalaNotFoundException;
import com.Aerolinea.Aerolinea.repository.EscalaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EscalaServiceTest {

    @Mock
    private EscalaRepository escalaRepository;

    @InjectMocks
    private EscalaService escalaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddEscala() {
        Escala escalaToAdd = new Escala();
        when(escalaRepository.save(escalaToAdd)).thenReturn(escalaToAdd);

        Escala result = escalaService.addEscala(escalaToAdd);

        assertNotNull(result);
        assertEquals(escalaToAdd, result);
    }

    @Test
    void testGetEscalas() {
        List<Escala> escalas = new ArrayList<>();
        when(escalaRepository.findAll()).thenReturn(escalas);

        List<Escala> result = escalaService.getEscalas();

        assertNotNull(result);
        assertEquals(escalas, result);
    }

    @Test
    void testGetEscala() {
        Long id = 1L;
        Escala escala = new Escala();
        when(escalaRepository.findById(id)).thenReturn(Optional.of(escala));

        Escala result = escalaService.getEscala(id);

        assertNotNull(result);
        assertEquals(escala, result);
    }

    @Test
    void testGetEscalaNotFound() {
        Long id = 1L;
        when(escalaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EscalaNotFoundException.class, () -> {
            escalaService.getEscala(id);
        });
    }

    @Test
    void testDeleteEscala() {
        Long id = 1L;
        Escala escalaToDelete = new Escala();
        when(escalaRepository.findById(id)).thenReturn(Optional.of(escalaToDelete));

        Escala result = escalaService.deleteEscala(id);

        assertNotNull(result);
        assertEquals(escalaToDelete, result);
        verify(escalaRepository, times(1)).delete(escalaToDelete);
    }

    @Test
    void testEditEscala() {
        Long id = 1L;
        Escala escalaToEdit = new Escala();
        Escala updatedEscala = new Escala();
        when(escalaRepository.findById(id)).thenReturn(Optional.of(escalaToEdit));
        when(escalaRepository.save(escalaToEdit)).thenReturn(updatedEscala);

        Escala result = escalaService.editEscala(id, escalaToEdit);

        assertNotNull(result);
        assertEquals(updatedEscala, result);
        assertEquals(escalaToEdit.getPaisOrigen(), updatedEscala.getPaisOrigen());
        assertEquals(escalaToEdit.getPaisDestino(), updatedEscala.getPaisDestino());
        assertEquals(escalaToEdit.getCiudadOrigen(), updatedEscala.getCiudadOrigen());
        assertEquals(escalaToEdit.getCiudadDestino(), updatedEscala.getCiudadDestino());
        assertEquals(escalaToEdit.getAeropuertoOrigen(), updatedEscala.getAeropuertoOrigen());
        assertEquals(escalaToEdit.getAeropuertoDestino(), updatedEscala.getAeropuertoDestino());
        assertEquals(escalaToEdit.getFechaOrigen(), updatedEscala.getFechaOrigen());
        assertEquals(escalaToEdit.getFechaDestino(), updatedEscala.getFechaDestino());
    }
}
