package com.Aerolinea.Aerolinea.model.exception;

import java.text.MessageFormat;

public class AerolineaNotFoundException extends RuntimeException{

    public AerolineaNotFoundException(final Long id){
        super(MessageFormat.format("Could not find aerolinea with id: {0}", id));
    }
}
