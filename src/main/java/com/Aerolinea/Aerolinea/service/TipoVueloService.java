package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.TipoVuelo;
import com.Aerolinea.Aerolinea.model.Vuelo;
import com.Aerolinea.Aerolinea.model.exception.TipoVueloNotFoundException;
import com.Aerolinea.Aerolinea.model.exception.VueloIsAlreadyAssignedException;
import com.Aerolinea.Aerolinea.repository.TipoVueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TipoVueloService {

    private final TipoVueloRepository tipoVueloRepository;
    private final VueloService vueloService;

    @Autowired
    public TipoVueloService(TipoVueloRepository tipoVueloRepository, VueloService vueloService) {
        this.tipoVueloRepository = tipoVueloRepository;
        this.vueloService = vueloService;
    }

    public TipoVuelo addTipoVuelo(TipoVuelo tipoVuelo){
        return tipoVueloRepository.save(tipoVuelo);
    }

    public List<TipoVuelo> getTiposVuelo(){
        return StreamSupport.
                stream(tipoVueloRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public TipoVuelo getTipoVuelo(Long id){
        return tipoVueloRepository.findById(id).orElseThrow(() ->
                new TipoVueloNotFoundException(id));
    }

    public TipoVuelo deleteTipoVuelo(Long id){
        TipoVuelo tipoVuelo = getTipoVuelo(id);
        tipoVueloRepository.delete(tipoVuelo);
        return tipoVuelo;
    }

    @Transactional
    public TipoVuelo editTipoVuelo(Long id, TipoVuelo tipoVuelo){
        TipoVuelo tipoVueloToEdit = getTipoVuelo(id);
        tipoVueloToEdit.setNombre(tipoVuelo.getNombre());
        return tipoVuelo;
    }

    @Transactional
    public TipoVuelo addVueloToTipoVuelo(Long idTipoVuelo, Long idVuelo){
        TipoVuelo tipoVuelo = getTipoVuelo(idTipoVuelo);
        Vuelo vuelo = vueloService.getVuelo(idVuelo);
        if(Objects.nonNull(vuelo.getTipoVuelo())){
            throw new VueloIsAlreadyAssignedException(idVuelo, vuelo.getTipoVuelo().getId());
        }
        tipoVuelo.addVuelo(vuelo);
        vuelo.setTipoVuelo(tipoVuelo);
        return tipoVuelo;
    }

    @Transactional
    public TipoVuelo removeVueloFromTipoVuelo(Long idTipoVuelo, Long idVuelo){
        TipoVuelo tipoVuelo = getTipoVuelo(idTipoVuelo);
        Vuelo vuelo = vueloService.getVuelo(idVuelo);
        tipoVuelo.removeVuelo(vuelo);
        return tipoVuelo;
    }
}
