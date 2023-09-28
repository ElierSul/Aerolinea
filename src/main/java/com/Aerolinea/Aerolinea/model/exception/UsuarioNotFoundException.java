package com.Aerolinea.Aerolinea.model.exception;

import java.text.MessageFormat;

public class UsuarioNotFoundException extends RuntimeException{

    public UsuarioNotFoundException(final Long id){
        super(MessageFormat.format("Could not find usuario with id: {0}", id));
    }
}
