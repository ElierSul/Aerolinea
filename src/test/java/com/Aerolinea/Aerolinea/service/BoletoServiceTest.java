package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.exception.BoletoNotFoundException;
import com.Aerolinea.Aerolinea.repository.BoletoRepository;
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

public class BoletoServiceTest {

    @Mock
    private BoletoRepository boletoRepository;

    @InjectMocks
    private BoletoService boletoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddBoleto() {
        Boleto boletoToAdd = new Boleto();
        when(boletoRepository.save(boletoToAdd)).thenReturn(boletoToAdd);

        Boleto result = boletoService.addBoleto(boletoToAdd);

        assertNotNull(result);
        assertEquals(boletoToAdd, result);
    }

    @Test
    void testGetBoletos() {
        List<Boleto> boletos = new ArrayList<>();
        when(boletoRepository.findAll()).thenReturn(boletos);

        List<Boleto> result = boletoService.getBoletos();

        assertNotNull(result);
        assertEquals(boletos, result);
    }

    @Test
    void testGetBoleto() {
        Long id = 1L;
        Boleto boleto = new Boleto();
        when(boletoRepository.findById(id)).thenReturn(Optional.of(boleto));

        Boleto result = boletoService.getBoleto(id);

        assertNotNull(result);
        assertEquals(boleto, result);
    }

    @Test
    void testGetBoletoNotFound() {
        Long id = 1L;
        when(boletoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BoletoNotFoundException.class, () -> {
            boletoService.getBoleto(id);
        });
    }

    @Test
    void testDeleteBoleto() {
        Long id = 1L;
        Boleto boletoToDelete = new Boleto();
        when(boletoRepository.findById(id)).thenReturn(Optional.of(boletoToDelete));

        Boleto result = boletoService.deleteBoleto(id);

        assertNotNull(result);
        assertEquals(boletoToDelete, result);
        verify(boletoRepository, times(1)).delete(boletoToDelete);
    }

    @Test
    void testEditBoleto() {
        Long id = 1L;
        Boleto boletoToEdit = new Boleto();
        Boleto updatedBoleto = new Boleto();
        when(boletoRepository.findById(id)).thenReturn(Optional.of(boletoToEdit));
        when(boletoRepository.save(boletoToEdit)).thenReturn(updatedBoleto);

        Boleto result = boletoService.editBoleto(id, boletoToEdit);

        assertNotNull(result);
        assertEquals(updatedBoleto, result);
        assertEquals(boletoToEdit.getLugar(), updatedBoleto.getLugar());
    }
}
