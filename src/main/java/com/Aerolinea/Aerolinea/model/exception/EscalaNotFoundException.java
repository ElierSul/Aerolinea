package com.Aerolinea.Aerolinea.model.exception;

import java.text.MessageFormat;

public class EscalaNotFoundException extends RuntimeException {

    public EscalaNotFoundException(final Long id){
        super(MessageFormat.format("Could not find escala with id: {0}", id));
    }
}
