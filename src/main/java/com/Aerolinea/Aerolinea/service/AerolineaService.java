package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.Aerolinea;
import com.Aerolinea.Aerolinea.model.Vuelo;
import com.Aerolinea.Aerolinea.model.exception.AerolineaNotFoundException;
import com.Aerolinea.Aerolinea.model.exception.VueloIsAlreadyAssignedException;
import com.Aerolinea.Aerolinea.repository.AerolineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AerolineaService {

    private final AerolineaRepository aerolineaRepository;
    private final VueloService vueloService;

    @Autowired
    public AerolineaService(AerolineaRepository aerolineaRepository, VueloService vueloService) {
        this.aerolineaRepository = aerolineaRepository;
        this.vueloService = vueloService;
    }

    public Aerolinea addAerolinea(Aerolinea aerolinea){
        return aerolineaRepository.save(aerolinea);
    }

    public List<Aerolinea> getAerolineas(){
        return StreamSupport.
                stream(aerolineaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Aerolinea getAerolinea(Long id){
        return aerolineaRepository.findById(id).orElseThrow(() ->
                new AerolineaNotFoundException(id));
    }

    public Aerolinea deleteAerolinea(Long id){
        Aerolinea aerolinea = getAerolinea(id);
        aerolineaRepository.delete(aerolinea);
        return aerolinea;
    }

    @Transactional
    public Aerolinea editAerolinea(Long id, Aerolinea aerolinea){
        Aerolinea aerolineaToEdit = getAerolinea(id);
        aerolineaToEdit.setNombre(aerolinea.getNombre());
        return aerolinea;
    }

    @Transactional
    public Aerolinea addVueloToAerolinea(Long idAerolinea, Long idVuelo){
        Aerolinea aerolinea = getAerolinea(idAerolinea);
        Vuelo vuelo = vueloService.getVuelo(idVuelo);
        if(Objects.nonNull(vuelo.getAerolinea())){
            throw new VueloIsAlreadyAssignedException(idVuelo, vuelo.getAerolinea().getId());
        }
        aerolinea.addVuelo(vuelo);
        vuelo.setAerolinea(aerolinea);
        return aerolinea;
    }

    @Transactional
    public Aerolinea removeVueloFromAerolinea(Long idAerolinea, Long idVuelo){
        Aerolinea aerolinea = getAerolinea(idAerolinea);
        Vuelo vuelo = vueloService.getVuelo(idVuelo);
        aerolinea.removeVuelo(vuelo);
        return aerolinea;
    }

}
