package com.Aerolinea.Aerolinea.model.exception;

import java.text.MessageFormat;

public class MedioPagoNotFoundException extends RuntimeException{

    public MedioPagoNotFoundException(final Long id){
        super(MessageFormat.format("Could not find medioPago with id: {0}", id));
    }
}
