package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.MedioPago;
import com.Aerolinea.Aerolinea.model.exception.BoletoIsAlreadyAssignedException;
import com.Aerolinea.Aerolinea.model.exception.MedioPagoNotFoundException;
import com.Aerolinea.Aerolinea.repository.MedioPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MedioPagoService {

    private final MedioPagoRepository medioPagoRepository;
    private final BoletoService boletoService;

    @Autowired
    public MedioPagoService(MedioPagoRepository medioPagoRepository, BoletoService boletoService) {
        this.medioPagoRepository = medioPagoRepository;
        this.boletoService = boletoService;
    }

    public MedioPago addMedioPago(MedioPago medioPago){
        return medioPagoRepository.save(medioPago);
    }

    public List<MedioPago> getMediosPago(){
        return StreamSupport.
                stream(medioPagoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public MedioPago getMedioPago(Long id){
        return medioPagoRepository.findById(id).orElseThrow(() ->
                new MedioPagoNotFoundException(id));
    }

    public MedioPago deleteMedioPago(Long id){
        MedioPago medioPago = getMedioPago(id);
        medioPagoRepository.delete(medioPago);
        return medioPago;
    }

    @Transactional
    public MedioPago editMedioPago(Long id, MedioPago medioPago){
        MedioPago medioPagoToEdit = getMedioPago(id);
        medioPagoToEdit.setPse(medioPago.isPse());
        medioPagoToEdit.setTarjetaCredito(medioPago.isTarjetaCredito());
        medioPagoToEdit.setTarjetaDebito(medioPago.isTarjetaDebito());
        return medioPagoToEdit;
    }

    @Transactional
    public MedioPago addBoletoToMedioPago(Long idMedioPago, Long idBoleto){
        MedioPago medioPago = getMedioPago(idMedioPago);
        Boleto boleto = boletoService.getBoleto(idBoleto);
        if(Objects.nonNull(boleto.getMedioPago())){
            throw new BoletoIsAlreadyAssignedException(idBoleto, boleto.getMedioPago().getId());
        }
        medioPago.addBoleto(boleto);
        boleto.setMedioPago(medioPago);
        return medioPago;
    }

    @Transactional
    public MedioPago removeBoletoFromMedioPago(Long idMedioPago, Long idBoleto){
        MedioPago medioPago = getMedioPago(idMedioPago);
        Boleto boleto = boletoService.getBoleto(idBoleto);
        medioPago.removeBoleto(boleto);
        return medioPago;
    }
}
