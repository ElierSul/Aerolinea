package com.Aerolinea.Aerolinea.model.exception;

import java.text.MessageFormat;

public class TipoVueloNotFoundException extends RuntimeException{

    public TipoVueloNotFoundException(final Long id){
        super(MessageFormat.format("Could not find tipo vuelo with id: {0}", id));
    }
}
