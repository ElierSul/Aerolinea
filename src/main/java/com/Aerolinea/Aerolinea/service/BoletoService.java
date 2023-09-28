package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.exception.BoletoNotFoundException;
import com.Aerolinea.Aerolinea.repository.BoletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BoletoService {

    private final BoletoRepository boletoRepository;

    @Autowired
    public BoletoService(BoletoRepository boletoRepository) {
        this.boletoRepository = boletoRepository;
    }

    public Boleto addBoleto(Boleto boleto){
        return boletoRepository.save(boleto);
    }

    public List<Boleto> getBoletos(){
        return StreamSupport
                .stream(boletoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Boleto getBoleto(Long id){
        return boletoRepository.findById(id).orElseThrow(() ->
                new BoletoNotFoundException(id));
    }

    public Boleto deleteBoleto(Long id){
        Boleto boleto = getBoleto(id);
        boletoRepository.delete(boleto);
        return boleto;
    }

    @Transactional
    public Boleto editBoleto(Long id, Boleto boleto){
        Boleto boletoToEdit = getBoleto(id);
        boletoToEdit.setPago(boleto.getPago());
        boletoToEdit.setSerialNumber(boleto.getSerialNumber());
        return boletoToEdit;
    }
}
