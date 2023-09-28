package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.Escala;
import com.Aerolinea.Aerolinea.model.Traslado;
import com.Aerolinea.Aerolinea.model.exception.EscalaIsAlreadyAssignedException;
import com.Aerolinea.Aerolinea.model.exception.TrasladoNotFoundException;
import com.Aerolinea.Aerolinea.repository.TrasladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TrasladoService {

    private final TrasladoRepository trasladoRepository;
    private final EscalaService escalaService;

    @Autowired
    public TrasladoService(TrasladoRepository trasladoRepository, EscalaService escalaService) {
        this.trasladoRepository = trasladoRepository;
        this.escalaService = escalaService;
    }

    public Traslado addTraslado(Traslado traslado){
        return trasladoRepository.save(traslado);
    }

    public List<Traslado> getTraslados(){
        return StreamSupport.
                stream(trasladoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Traslado getTraslado(Long id){
        return trasladoRepository.findById(id).orElseThrow(()->
                new TrasladoNotFoundException(id));
    }

    public Traslado deleteTraslado(Long id){
        Traslado traslado = getTraslado(id);
        trasladoRepository.delete(traslado);
        return traslado;
    }
    @Transactional
    public Traslado editTraslado(Long id, Traslado traslado){
        Traslado trasladoToEdit = getTraslado(id);
        trasladoToEdit.setAeropuerto(traslado.getAeropuerto());
        trasladoToEdit.setCiudad(traslado.getCiudad());
        trasladoToEdit.setEstado(traslado.getEstado());
        trasladoToEdit.setFechaDestino(traslado.getFechaDestino());
        trasladoToEdit.setFechaOrigen(traslado.getFechaOrigen());
        trasladoToEdit.setPais(traslado.getPais());
        trasladoToEdit.setSoloIda(traslado.isSoloIda());
        trasladoToEdit.setIdaVuelta(traslado.isIdaVuelta());
        return trasladoToEdit;
    }

    @Transactional
    public Traslado addEscalaToTraslado(Long idTraslado, Long idEscala){
        Traslado traslado = getTraslado(idTraslado);
        Escala escala = escalaService.getEscala(idEscala);
        if(Objects.nonNull(escala.getTraslado())){
            throw new EscalaIsAlreadyAssignedException(idEscala, escala.getTraslado().getId());
        }
        traslado.addEscala(escala);
        escala.setTraslado(traslado);
        return traslado;
    }

    @Transactional
    public Traslado removeEscalaToTraslado(Long idTraslado, Long idEscala){
        Traslado traslado = getTraslado(idTraslado);
        Escala escala = escalaService.getEscala(idEscala);
        traslado.removeEscala(escala);
        return traslado;
    }
}
