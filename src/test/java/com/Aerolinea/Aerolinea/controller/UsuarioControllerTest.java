package com.Aerolinea.Aerolinea.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.Aerolinea.Aerolinea.constants.BoletoConstantes;
import com.Aerolinea.Aerolinea.model.Usuario;
import com.Aerolinea.Aerolinea.model.dto.UsuarioDto;
import com.Aerolinea.Aerolinea.service.UsuarioService;
import com.Aerolinea.Aerolinea.util.BoletoUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsuarioControllerTest {
    private UsuarioController usuarioController;
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        usuarioService = mock(UsuarioService.class);
        usuarioController = new UsuarioController(usuarioService);
    }

    @Test
    public void testAddUsuario() {
        // Datos de ejemplo para el mapa de solicitud
        Map<String, String> requestMap = Map.of("username", "usuario1", "password", "contraseña123");

        // Configurar el comportamiento del servicio al registrar un usuario
        when(usuarioService.signUp(requestMap)).thenReturn(
                BoletoUtils.getResponseEntity("Usuario registrado con éxito", HttpStatus.OK)
        );

        // Llamar al método del controlador
        ResponseEntity<String> responseEntity = usuarioController.addUsuario(requestMap);

        // Verificar que la respuesta sea exitosa (código 200) y que el mensaje sea el esperado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Usuario registrado con éxito", responseEntity.getBody());
    }

    @Test
    public void testLogin() {
        // Datos de ejemplo para el mapa de solicitud
        Map<String, String> requestMap = Map.of("username", "usuario1", "password", "contraseña123");

        // Configurar el comportamiento del servicio al realizar el inicio de sesión
        when(usuarioService.login(requestMap)).thenReturn(
                BoletoUtils.getResponseEntity("Inicio de sesión exitoso", HttpStatus.OK)
        );

        // Llamar al método del controlador
        ResponseEntity<String> responseEntity = usuarioController.login(requestMap);

        // Verificar que la respuesta sea exitosa (código 200) y que el mensaje sea el esperado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Inicio de sesión exitoso", responseEntity.getBody());
    }

    @Test
    public void testGetUsuarios() {
        // Datos de ejemplo para una lista de usuarios
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1L, "Usuario 1"));
        usuarios.add(new Usuario(2L, "Usuario 2"));

        // Configurar el comportamiento del servicio al obtener todos los usuarios
        when(usuarioService.getUsuarios()).thenReturn(usuarios);

        // Llamar al método del controlador
        ResponseEntity<List<UsuarioDto>> responseEntity = usuarioController.getUsuarios();

        // Verificar que la respuesta sea exitosa (código 200) y que la lista de usuarios retornado sea el mismo que el creado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuarios.size(), responseEntity.getBody().size());
    }


}
