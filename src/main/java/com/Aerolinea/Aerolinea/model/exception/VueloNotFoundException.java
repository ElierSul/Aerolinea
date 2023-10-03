package com.Aerolinea.Aerolinea.model.exception;

import java.text.MessageFormat;

public class VueloNotFoundException extends RuntimeException{

    public VueloNotFoundException(final Long id){
        super(MessageFormat.format("Could not find vuelo with id: {0}", id));
    }
}
