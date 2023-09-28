package com.Aerolinea.Aerolinea.model.exception;

import java.text.MessageFormat;

public class BoletoIsAlreadyAssignedException extends RuntimeException{

    public BoletoIsAlreadyAssignedException(final Long idBoleto, final Long idMedioPago){
        super(MessageFormat.format("Boleto: {0} is already assigned to medioPago: {1}", idBoleto, idMedioPago));
    }
}
