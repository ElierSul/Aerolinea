package com.Aerolinea.Aerolinea.model.exception;

import java.text.MessageFormat;

public class TrasladoNotFoundException extends RuntimeException {

    public TrasladoNotFoundException(final Long id){
        super(MessageFormat.format("Could not find traslado with id: {0}", id));
    }

}
