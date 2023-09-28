package com.Aerolinea.Aerolinea.model.exception;

import java.text.MessageFormat;

public class BoletoNotFoundException extends RuntimeException{

    public BoletoNotFoundException(final Long id){
        super(MessageFormat.format("Could not find boleto with id: {0}", id));
    }
}
