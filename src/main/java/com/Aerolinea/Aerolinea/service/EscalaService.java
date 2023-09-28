package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.Escala;
import com.Aerolinea.Aerolinea.model.exception.EscalaNotFoundException;
import com.Aerolinea.Aerolinea.repository.EscalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EscalaService {

    private final EscalaRepository escalaRepository;

    @Autowired
    public EscalaService(EscalaRepository escalaRepository) {
        this.escalaRepository = escalaRepository;
    }

    public Escala addEscala(Escala escala){
        return escalaRepository.save(escala);
    }

    public List<Escala> getEscalas(){
        return StreamSupport.
                stream(escalaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Escala getEscala(Long id){
        return escalaRepository.findById(id).orElseThrow(() ->
                new EscalaNotFoundException(id));
    }

    public Escala deleteEscala(Long id){
        Escala escala = getEscala(id);
        escalaRepository.delete(escala);
        return escala;
    }

    @Transactional
    public Escala editEscala(Long id, Escala escala){
        Escala escalaToEdit = getEscala(id);
        escalaToEdit.setSerialNumber(escala.getSerialNumber());
        return escalaToEdit;
    }
}
