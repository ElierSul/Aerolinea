package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.Escala;
import com.Aerolinea.Aerolinea.model.MedioPago;
import com.Aerolinea.Aerolinea.model.Vuelo;
import com.Aerolinea.Aerolinea.model.exception.BoletoIsAlreadyAssignedVueloException;
import com.Aerolinea.Aerolinea.model.exception.EscalaIsAlreadyAssignedException;
import com.Aerolinea.Aerolinea.model.exception.VueloNotFoundException;
import com.Aerolinea.Aerolinea.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VueloService {

    private final VueloRepository vueloRepository;
    private final EscalaService escalaService;
    private final BoletoService boletoService;

    @Autowired
    public VueloService(VueloRepository vueloRepository, EscalaService escalaService, BoletoService boletoService) {
        this.vueloRepository = vueloRepository;
        this.escalaService = escalaService;
        this.boletoService = boletoService;
    }

    public Vuelo addVuelo(Vuelo vuelo){
        return  vueloRepository.save(vuelo);
    }


    public List<Vuelo> getVuelos(){
        return StreamSupport.
                stream(vueloRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Vuelo getVuelo(Long id){
        return vueloRepository.findById(id).orElseThrow(() ->
                new VueloNotFoundException(id));
    }

    public Vuelo deleteVuelo(Long id){
        Vuelo vuelo = getVuelo(id);
        vueloRepository.delete(vuelo);
        return vuelo;
    }

    @Transactional
    public Vuelo editVuelo(Long id, Vuelo vuelo){
        Vuelo vueloToEdit = getVuelo(id);
        vueloToEdit.setLugaresDisponibles(vuelo.getLugaresDisponibles());
        vueloToEdit.setCosto(vuelo.getCosto());
        return vueloToEdit;
    }

    @Transactional
    public Vuelo addEscalaToVuelo(Long idVuelo, Long idEscala){
        Vuelo vuelo = getVuelo(idVuelo);
        Escala escala = escalaService.getEscala(idEscala);
        if (Objects.nonNull(escala.getVuelo())) {
            throw new EscalaIsAlreadyAssignedException(idEscala, idVuelo);
        }
        vuelo.addEscala(escala);
        escala.setVuelo(vuelo);
        return vuelo;
    }

    @Transactional
    public Vuelo removeEscalaFromVuelo(Long idVuelo, Long idEscala){
        Vuelo vuelo = getVuelo(idVuelo);
        Escala escala = escalaService.getEscala(idEscala);
        vuelo.removeEscala(escala);
        return vuelo;
    }

    @Transactional
    public Vuelo addBoletoToVuelo(Long idVuelo, Long idBoleto){
        Vuelo vuelo = getVuelo(idVuelo);
        Boleto boleto = boletoService.getBoleto(idBoleto);
        if (Objects.nonNull(boleto.getVuelo())) {
            throw new BoletoIsAlreadyAssignedVueloException(idBoleto, idVuelo);
        }
        vuelo.addBoleto(boleto);
        boleto.setVuelo(vuelo);
        return vuelo;
    }

    @Transactional
    public Vuelo removeBoletoToVuelo(Long idVuelo, Long idBoleto){
        Vuelo vuelo = getVuelo(idVuelo);
        Boleto boleto = boletoService.getBoleto(idBoleto);
        vuelo.removeBoleto(boleto);
        return vuelo;
    }

    /*public List<Vuelo> getVuelosQueCumplenRequisitos(String ciudadOrigen, String ciudadDestino, Date fechaOrigen) {
        List<Vuelo> vuelos = vueloRepository.findAll(); // Supongo que tienes un método para obtener todos los vuelos

        List<Vuelo> vuelosQueCumplen = new ArrayList<>();

        for (Vuelo vuelo : vuelos) {
            boolean cumpleCiudadOrigen = false;
            boolean cumpleCiudadDestino = false;
            boolean cumpleFechaOrigen = false;

            int i = 0;
            while (i < vuelo.getEscalas().size() && !cumpleCiudadDestino) {
                Escala escala = vuelo.getEscalas().get(i);

                if (escala.getCiudadOrigen().equals(ciudadOrigen)) {
                    cumpleCiudadOrigen = true;
                }

                if (escala.getFechaOrigen().equals(fechaOrigen)) {
                    cumpleFechaOrigen = true;
                }

                if (cumpleCiudadOrigen && cumpleFechaOrigen) {
                    cumpleCiudadDestino = escala.getCiudadDestino().equals(ciudadDestino);
                }

                i++;
            }

            if (cumpleCiudadOrigen && cumpleFechaOrigen && cumpleCiudadDestino) {
                vuelosQueCumplen.add(vuelo);
            }
        }

        return vuelosQueCumplen;
    }*/

}
