package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.Usuario;
import com.Aerolinea.Aerolinea.model.exception.BoletoIsAlreadyAssignedUsuarioException;
import com.Aerolinea.Aerolinea.repository.UsuarioRepository;
import com.Aerolinea.Aerolinea.service.BoletoService;
import com.Aerolinea.Aerolinea.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BoletoService boletoService;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testEditUsuario_Status() {
        Long id = 1L;
        Usuario usuarioToEdit = new Usuario();
        usuarioToEdit.setStatus("true"); // Cambiar a true
        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.of(usuarioToEdit));
        when(usuarioRepository.save(usuarioToEdit)).thenReturn(usuarioToEdit);

        Usuario result = usuarioService.editUsuario(id, usuarioToEdit);

        assertNotNull(result);
        assertEquals(usuarioToEdit, result);
        assertEquals("true", result.getStatus()); // Verificar que status sea true
        assertTrue(result.isEquipaje()); // Mantener equipaje como true
    }
    @Test
    void testAddBoletoToUsuario_Status() {
        Long idUsuario = 1L;
        Long idBoleto = 2L;
        Usuario usuario = new Usuario();

        // Crear un mock de la clase Boleto
        Boleto boletoMock = mock(Boleto.class);

        // Configurar el comportamiento del mock para el método setUsuario
        when(boletoService.getBoleto(idBoleto)).thenReturn(boletoMock);

        // Llamar al método que deseas probar
        BoletoIsAlreadyAssignedUsuarioException exception = assertThrows(BoletoIsAlreadyAssignedUsuarioException.class, () -> {
            usuarioService.addBoletoToUsuario(idUsuario, idBoleto);
        });

        // Verificar que se haya lanzado la excepción
        assertNotNull(exception);

        // Verificar que el método setUsuario se haya llamado en el mock
        verify(boletoMock).setUsuario(idUsuario);
    }
}


