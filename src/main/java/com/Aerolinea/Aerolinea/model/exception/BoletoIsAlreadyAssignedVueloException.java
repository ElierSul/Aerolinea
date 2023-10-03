package com.Aerolinea.Aerolinea.model.exception;

import java.text.MessageFormat;

public class BoletoIsAlreadyAssignedVueloException extends RuntimeException{
    public BoletoIsAlreadyAssignedVueloException(final Long idBoleto, final Long idVuelo){
        super(MessageFormat.format("Boleto: {0} is already assigned to vuelo: {1}", idBoleto, idVuelo));
    }
}
