package com.Aerolinea.Aerolinea.model.exception;

import java.text.MessageFormat;

public class BoletoIsAlreadyAssignedUsuarioException extends RuntimeException{

    public BoletoIsAlreadyAssignedUsuarioException(final Long idBoleto, final Long idUsuario){
        super(MessageFormat.format("Boleto: {0} is already assigned to medioPago: {1}", idBoleto, idUsuario));
    }
}
