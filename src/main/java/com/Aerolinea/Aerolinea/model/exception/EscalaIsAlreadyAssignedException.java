package com.Aerolinea.Aerolinea.model.exception;

import java.text.MessageFormat;

public class EscalaIsAlreadyAssignedException extends RuntimeException {

    public EscalaIsAlreadyAssignedException(final Long idEscala, final Long idTraslado){
        super(MessageFormat.format("Escala: {0} is already assigned to traslado: {1}", idEscala, idTraslado));
    }
}
