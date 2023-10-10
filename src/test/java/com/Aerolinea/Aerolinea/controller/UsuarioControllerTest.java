package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@WebMvcTest(UsuarioController.class)//para ingresar al controlador especifico
class UsuarioControllerTest {

    private UsuarioController usuarioController;
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        usuarioService = mock(UsuarioService.class);
        usuarioController = new UsuarioController(usuarioService);
    }

    @Test
    public void testAddUsuario() {

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("nombre", "Usuario de Prueba");
        requestMap.put("email", "usuario@example.com");

        when(usuarioService.signUp(requestMap)).thenReturn(new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK));

        ResponseEntity<String> responseEntity = usuarioController.addUsuario(requestMap);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Usuario registrado exitosamente", responseEntity.getBody());
    }
}


