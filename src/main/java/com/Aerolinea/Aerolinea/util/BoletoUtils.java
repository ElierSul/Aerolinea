package com.Aerolinea.Aerolinea.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BoletoUtils {

    private BoletoUtils(){}

    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpStatus){
        return new ResponseEntity<String>("Mensaje: " + message, httpStatus);
    }
}
