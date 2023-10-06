package com.Aerolinea.Aerolinea.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUsuarioService {

    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<String> login (Map<String, String> requestMap);
}
