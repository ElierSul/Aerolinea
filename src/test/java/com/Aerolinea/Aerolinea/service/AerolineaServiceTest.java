package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.Aerolinea;
import com.Aerolinea.Aerolinea.model.Vuelo;
import com.Aerolinea.Aerolinea.model.exception.AerolineaNotFoundException;
import com.Aerolinea.Aerolinea.model.exception.VueloIsAlreadyAssignedException;
import com.Aerolinea.Aerolinea.repository.AerolineaRepository;
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

public class AerolineaServiceTest {

    @Mock
    private AerolineaRepository aerolineaRepository;

    @Mock
    private VueloService vueloService;

    @InjectMocks
    private AerolineaService aerolineaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddAerolinea() {
        Aerolinea aerolineaToAdd = new Aerolinea();
        when(aerolineaRepository.save(aerolineaToAdd)).thenReturn(aerolineaToAdd);

        Aerolinea result = aerolineaService.addAerolinea(aerolineaToAdd);

        assertNotNull(result);
        assertEquals(aerolineaToAdd, result);
    }

    @Test
    void testGetAerolineas() {
        List<Aerolinea> aerolineas = new ArrayList<>();
        when(aerolineaRepository.findAll()).thenReturn(aerolineas);

        List<Aerolinea> result = aerolineaService.getAerolineas();

        assertNotNull(result);
        assertEquals(aerolineas, result);
    }

    @Test
    void testGetAerolinea() {
        Long id = 1L;
        Aerolinea aerolinea = new Aerolinea();
        when(aerolineaRepository.findById(id)).thenReturn(Optional.of(aerolinea));

        Aerolinea result = aerolineaService.getAerolinea(id);

        assertNotNull(result);
        assertEquals(aerolinea, result);
    }

    @Test
    void testGetAerolineaNotFound() {
        Long id = 1L;
        when(aerolineaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(AerolineaNotFoundException.class, () -> {
            aerolineaService.getAerolinea(id);
        });
    }

    @Test
    void testDeleteAerolinea() {
        Long id = 1L;
        Aerolinea aerolineaToDelete = new Aerolinea();
        when(aerolineaRepository.findById(id)).thenReturn(Optional.of(aerolineaToDelete));

        Aerolinea result = aerolineaService.deleteAerolinea(id);

        assertNotNull(result);
        assertEquals(aerolineaToDelete, result);
        verify(aerolineaRepository, times(1)).delete(aerolineaToDelete);
    }

    @Test
    void testEditAerolinea() {
        Long id = 1L;
        Aerolinea aerolineaToEdit = new Aerolinea();
        Aerolinea updatedAerolinea = new Aerolinea();
        when(aerolineaRepository.findById(id)).thenReturn(Optional.of(aerolineaToEdit));
        when(aerolineaRepository.save(aerolineaToEdit)).thenReturn(updatedAerolinea);

        Aerolinea result = aerolineaService.editAerolinea(id, aerolineaToEdit);

        assertNotNull(result);
        assertEquals(updatedAerolinea, result);
        assertEquals(aerolineaToEdit.getNombre(), updatedAerolinea.getNombre());
    }

    @Test
    void testAddVueloToAerolinea() {
        Long idAerolinea = 1L;
        Long idVuelo = 2L;
        Aerolinea aerolinea = new Aerolinea();
        Vuelo vuelo = new Vuelo();
        when(aerolineaRepository.findById(idAerolinea)).thenReturn(Optional.of(aerolinea));
        when(vueloService.getVuelo(idVuelo)).thenReturn(vuelo);

        Aerolinea result = aerolineaService.addVueloToAerolinea(idAerolinea, idVuelo);

        assertNotNull(result);
        assertEquals(aerolinea, result);
        assertEquals(vuelo, aerolinea.getVuelos().get(0));
        assertEquals(aerolinea, vuelo.getAerolinea());
    }

    @Test
    void testAddVueloToAerolineaVueloAlreadyAssigned() {
        Long idAerolinea = 1L;
        Long idVuelo = 2L;
        Aerolinea aerolinea = new Aerolinea();
        Vuelo vuelo = new Vuelo();
        vuelo.setAerolinea(aerolinea);
        when(aerolineaRepository.findById(idAerolinea)).thenReturn(Optional.of(aerolinea));
        when(vueloService.getVuelo(idVuelo)).thenReturn(vuelo);

        assertThrows(VueloIsAlreadyAssignedException.class, () -> {
            aerolineaService.addVueloToAerolinea(idAerolinea, idVuelo);
        });
    }

    @Test
    void testRemoveVueloFromAerolinea() {
        Long idAerolinea = 1L;
        Long idVuelo = 2L;
        Aerolinea aerolinea = new Aerolinea();
        Vuelo vuelo = new Vuelo();
        aerolinea.addVuelo(vuelo);
        when(aerolineaRepository.findById(idAerolinea)).thenReturn(Optional.of(aerolinea));
        when(vueloService.getVuelo(idVuelo)).thenReturn(vuelo);

        Aerolinea result = aerolineaService.removeVueloFromAerolinea(idAerolinea, idVuelo);

        assertNotNull(result);
        assertEquals(aerolinea, result);
        assertTrue(aerolinea.getVuelos().isEmpty());
    }
}

