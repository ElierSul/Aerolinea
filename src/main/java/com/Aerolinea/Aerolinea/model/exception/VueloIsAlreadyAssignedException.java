package com.Aerolinea.Aerolinea.model.exception;

import java.text.MessageFormat;

public class VueloIsAlreadyAssignedException extends RuntimeException{

    public VueloIsAlreadyAssignedException(final Long idVuelo, final Long idAerolinea){
        super(MessageFormat.format("Vuelo: {0} is already assigned to Aerolinea: {1}", idVuelo, idAerolinea));
    }
}
