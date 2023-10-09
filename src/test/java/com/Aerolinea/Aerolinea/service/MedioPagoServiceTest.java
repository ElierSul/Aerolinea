package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.MedioPago;
import com.Aerolinea.Aerolinea.model.exception.BoletoIsAlreadyAssignedMedioPagoException;
import com.Aerolinea.Aerolinea.model.exception.MedioPagoNotFoundException;
import com.Aerolinea.Aerolinea.repository.MedioPagoRepository;
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

public class MedioPagoServiceTest {

    @Mock
    private MedioPagoRepository medioPagoRepository;

    @Mock
    private BoletoService boletoService;

    @InjectMocks
    private MedioPagoService medioPagoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddMedioPago() {
        MedioPago medioPagoToAdd = new MedioPago();
        when(medioPagoRepository.save(medioPagoToAdd)).thenReturn(medioPagoToAdd);

        MedioPago result = medioPagoService.addMedioPago(medioPagoToAdd);

        assertNotNull(result);
        assertEquals(medioPagoToAdd, result);
    }

    @Test
    void testGetMediosPago() {
        List<MedioPago> mediosPago = new ArrayList<>();
        when(medioPagoRepository.findAll()).thenReturn(mediosPago);

        List<MedioPago> result = medioPagoService.getMediosPago();

        assertNotNull(result);
        assertEquals(mediosPago, result);
    }

    @Test
    void testGetMedioPago() {
        Long id = 1L;
        MedioPago medioPago = new MedioPago();
        when(medioPagoRepository.findById(id)).thenReturn(Optional.of(medioPago));

        MedioPago result = medioPagoService.getMedioPago(id);

        assertNotNull(result);
        assertEquals(medioPago, result);
    }

    @Test
    void testGetMedioPagoNotFound() {
        Long id = 1L;
        when(medioPagoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(MedioPagoNotFoundException.class, () -> {
            medioPagoService.getMedioPago(id);
        });
    }

    @Test
    void testDeleteMedioPago() {
        Long id = 1L;
        MedioPago medioPagoToDelete = new MedioPago();
        when(medioPagoRepository.findById(id)).thenReturn(Optional.of(medioPagoToDelete));

        MedioPago result = medioPagoService.deleteMedioPago(id);

        assertNotNull(result);
        assertEquals(medioPagoToDelete, result);
        verify(medioPagoRepository, times(1)).delete(medioPagoToDelete);
    }

    @Test
    void testEditMedioPago() {
        Long id = 1L;
        MedioPago medioPagoToEdit = new MedioPago();
        MedioPago updatedMedioPago = new MedioPago();
        when(medioPagoRepository.findById(id)).thenReturn(Optional.of(medioPagoToEdit));
        when(medioPagoRepository.save(medioPagoToEdit)).thenReturn(updatedMedioPago);

        MedioPago result = medioPagoService.editMedioPago(id, medioPagoToEdit);

        assertNotNull(result);
        assertEquals(updatedMedioPago, result);
        assertEquals(medioPagoToEdit.isPse(), updatedMedioPago.isPse());
        assertEquals(medioPagoToEdit.isTarjetaCredito(), updatedMedioPago.isTarjetaCredito());
        assertEquals(medioPagoToEdit.isTarjetaDebito(), updatedMedioPago.isTarjetaDebito());
    }

    @Test
    void testEditMedioPago_PSE() {
        Long id = 1L;
        MedioPago medioPagoToEdit = new MedioPago();
        MedioPago medioPago_WithOutToEdit = new MedioPago();
        medioPagoToEdit.setPse(true); // Cambiar a true
        when(medioPagoRepository.findById(id)).thenReturn(Optional.of(medioPagoToEdit));
        when(medioPagoRepository.save(medioPagoToEdit)).thenReturn(medioPago_WithOutToEdit);

        MedioPago result = medioPagoService.editMedioPago(id, medioPagoToEdit);

        assertNotNull(result);
        assertNotEquals(medioPago_WithOutToEdit, result);
        assertTrue(result.isPse());
        assertFalse(result.isTarjetaCredito()); // Verificar que Tarjeta de Crédito sea true
        assertFalse(result.isTarjetaDebito());
    }
    @Test
    void testEditMedioPago_TarjetaCredito() {
        Long id = 1L;
        MedioPago medioPagoToEdit = new MedioPago();
        MedioPago medioPago_WithOutToEdit = new MedioPago();
        medioPagoToEdit.setTarjetaCredito(true); // Cambiar a true
        when(medioPagoRepository.findById(id)).thenReturn(Optional.of(medioPagoToEdit));
        when(medioPagoRepository.save(medioPagoToEdit)).thenReturn(medioPago_WithOutToEdit);

        MedioPago result = medioPagoService.editMedioPago(id, medioPagoToEdit);

        assertNotNull(result);
        assertNotEquals(medioPago_WithOutToEdit, result);
        assertFalse(result.isPse());
        assertTrue(result.isTarjetaCredito()); // Verificar que Tarjeta de Crédito sea true
        assertFalse(result.isTarjetaDebito());
    }

    @Test
    void testEditMedioPago_TarjetaDebito() {
        Long id = 1L;
        MedioPago medioPagoToEdit = new MedioPago();
        MedioPago medioPago_WithOutToEdit = new MedioPago();
        medioPagoToEdit.setTarjetaDebito(true); // Cambiar a true
        when(medioPagoRepository.findById(id)).thenReturn(Optional.of(medioPagoToEdit));
        when(medioPagoRepository.save(medioPagoToEdit)).thenReturn(medioPago_WithOutToEdit);

        MedioPago result = medioPagoService.editMedioPago(id, medioPagoToEdit);

        assertNotNull(result);
        assertNotEquals(medioPago_WithOutToEdit, result);
        assertFalse(result.isPse());
        assertFalse(result.isTarjetaCredito()); // Verificar que Tarjeta de Crédito sea true
        assertTrue(result.isTarjetaDebito());
    }

    @Test
    void testAddBoletoToMedioPago() {
        Long idMedioPago = 1L;
        Long idBoleto = 2L;
        MedioPago medioPago = new MedioPago();
        Boleto boleto = new Boleto();
        when(medioPagoRepository.findById(idMedioPago)).thenReturn(Optional.of(medioPago));
        when(boletoService.getBoleto(idBoleto)).thenReturn(boleto);

        MedioPago result = medioPagoService.addBoletoToMedioPago(idMedioPago, idBoleto);

        assertNotNull(result);
        assertEquals(medioPago, result);
        assertEquals(boleto, medioPago.getBoletos().get(0));
        assertEquals(medioPago, boleto.getMedioPago());
    }

    @Test
    void testAddBoletoToMedioPagoBoletoAlreadyAssigned() {
        Long idMedioPago = 1L;
        Long idBoleto = 2L;
        MedioPago medioPago = new MedioPago();
        Boleto boleto = new Boleto();
        boleto.setMedioPago(medioPago);
        when(medioPagoRepository.findById(idMedioPago)).thenReturn(Optional.of(medioPago));
        when(boletoService.getBoleto(idBoleto)).thenReturn(boleto);

        assertThrows(BoletoIsAlreadyAssignedMedioPagoException.class, () -> {
            medioPagoService.addBoletoToMedioPago(idMedioPago, idBoleto);
        });
    }

    @Test
    void testRemoveBoletoFromMedioPago() {
        Long idMedioPago = 1L;
        Long idBoleto = 2L;
        MedioPago medioPago = new MedioPago();
        Boleto boleto = new Boleto();
        boleto.setMedioPago(medioPago);
        medioPago.addBoleto(boleto);
        when(medioPagoRepository.findById(idMedioPago)).thenReturn(Optional.of(medioPago));
        when(boletoService.getBoleto(idBoleto)).thenReturn(boleto);

        MedioPago result = medioPagoService.removeBoletoFromMedioPago(idMedioPago, idBoleto);

        assertEquals(medioPago, result);
        assertTrue(medioPago.getBoletos().isEmpty());

    }
}
