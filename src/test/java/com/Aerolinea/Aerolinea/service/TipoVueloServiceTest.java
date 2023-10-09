package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.TipoVuelo;
import com.Aerolinea.Aerolinea.model.Vuelo;
import com.Aerolinea.Aerolinea.model.exception.TipoVueloNotFoundException;
import com.Aerolinea.Aerolinea.model.exception.VueloIsAlreadyAssignedException;
import com.Aerolinea.Aerolinea.repository.TipoVueloRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TipoVueloServiceTest {

    @InjectMocks
    private TipoVueloService tipoVueloService;

    @Mock
    private TipoVueloRepository tipoVueloRepository;

    @Mock
    private VueloService vueloService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTipoVuelo() {
        TipoVuelo tipoVuelo = new TipoVuelo();
        when(tipoVueloRepository.save(tipoVuelo)).thenReturn(tipoVuelo);

        TipoVuelo result = tipoVueloService.addTipoVuelo(tipoVuelo);

        assertNotNull(result);
        assertEquals(tipoVuelo, result);
    }

    @Test
    void testGetTiposVuelo() {
        List<TipoVuelo> tipoVuelos = new ArrayList<>();
        when(tipoVueloRepository.findAll()).thenReturn(tipoVuelos);

        List<TipoVuelo> result = tipoVueloService.getTiposVuelo();

        assertNotNull(result);
        assertEquals(tipoVuelos, result);
    }

    @Test
    void testGetTipoVuelo() {
        Long id = 1L;
        TipoVuelo tipoVuelo = new TipoVuelo();
        when(tipoVueloRepository.findById(id)).thenReturn(Optional.of(tipoVuelo));

        TipoVuelo result = tipoVueloService.getTipoVuelo(id);

        assertNotNull(result);
        assertEquals(tipoVuelo, result);
    }

    @Test
    void testGetTipoVueloNotFound() {
        Long id = 1L;
        when(tipoVueloRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TipoVueloNotFoundException.class, () -> tipoVueloService.getTipoVuelo(id));
    }

    @Test
    void testDeleteTipoVuelo() {
        Long id = 1L;
        TipoVuelo tipoVuelo = new TipoVuelo();
        when(tipoVueloRepository.findById(id)).thenReturn(Optional.of(tipoVuelo));

        TipoVuelo result = tipoVueloService.deleteTipoVuelo(id);

        assertNotNull(result);
        assertEquals(tipoVuelo, result);
        verify(tipoVueloRepository, times(1)).delete(tipoVuelo);
    }

    @Test
    void testEditTipoVuelo() {
        Long id = 1L;
        TipoVuelo tipoVuelo = new TipoVuelo();
        TipoVuelo tipoVueloToUpdate = new TipoVuelo();
        tipoVueloToUpdate.setPrivado(true);
        when(tipoVueloRepository.findById(id)).thenReturn(Optional.of(tipoVuelo));

        TipoVuelo result = tipoVueloService.editTipoVuelo(id, tipoVueloToUpdate);

        assertNotNull(result);
        assertEquals(tipoVuelo, result);
        assertTrue(result.isPrivado());
    }

    @Test
    void testAddVueloToTipoVuelo() {
        Long idTipoVuelo = 1L;
        Long idVuelo = 2L;
        TipoVuelo tipoVuelo = new TipoVuelo();
        Vuelo vuelo = new Vuelo();
        when(tipoVueloRepository.findById(idTipoVuelo)).thenReturn(Optional.of(tipoVuelo));
        when(vueloService.getVuelo(idVuelo)).thenReturn(vuelo);

        TipoVuelo result = tipoVueloService.addVueloToTipoVuelo(idTipoVuelo, idVuelo);

        assertNotNull(result);
        assertEquals(tipoVuelo, result);
        assertEquals(tipoVuelo, vuelo.getTipoVuelo());
    }

    @Test
    void testAddVueloToTipoVueloAlreadyAssigned() {
        Long idTipoVuelo = 1L;
        Long idVuelo = 2L;
        TipoVuelo tipoVuelo = new TipoVuelo();
        Vuelo vuelo = new Vuelo();
        vuelo.setTipoVuelo(tipoVuelo);
        when(tipoVueloRepository.findById(idTipoVuelo)).thenReturn(Optional.of(tipoVuelo));
        when(vueloService.getVuelo(idVuelo)).thenReturn(vuelo);

        assertThrows(VueloIsAlreadyAssignedException.class, () -> tipoVueloService.addVueloToTipoVuelo(idTipoVuelo, idVuelo));
    }

    @Test
    void testRemoveVueloFromTipoVuelo() {
        Long idTipoVuelo = 1L;
        Long idVuelo = 2L;
        TipoVuelo tipoVuelo = new TipoVuelo();
        Vuelo vuelo = new Vuelo();
        vuelo.setTipoVuelo(tipoVuelo);
        when(tipoVueloRepository.findById(idTipoVuelo)).thenReturn(Optional.of(tipoVuelo));
        when(vueloService.getVuelo(idVuelo)).thenReturn(vuelo);

        TipoVuelo result = tipoVueloService.removeVueloFromTipoVuelo(idTipoVuelo, idVuelo);

        assertNotNull(result);
        assertEquals(tipoVuelo, result);
        assertNull(vuelo.getTipoVuelo());
    }
}

