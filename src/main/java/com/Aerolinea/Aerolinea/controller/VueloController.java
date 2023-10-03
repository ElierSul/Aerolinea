package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Vuelo;
import com.Aerolinea.Aerolinea.model.dto.VueloDto;
import com.Aerolinea.Aerolinea.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vuelos")
public class VueloController {

    private final VueloService vueloService;

    @Autowired
    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @PostMapping
    public ResponseEntity<VueloDto> addVuelo(@RequestBody final VueloDto vueloDto){
        Vuelo vuelo = vueloService.addVuelo(Vuelo.from(vueloDto));
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VueloDto>> getVuelos(){
        List<Vuelo> vuelos = vueloService.getVuelos();
        List<VueloDto> vueloDtos = vuelos.stream().map(VueloDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(vueloDtos, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<VueloDto> getVuelo(@PathVariable final Long id){
        Vuelo vuelo = vueloService.getVuelo(id);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<VueloDto> deleteVuelo(@PathVariable final Long id){
        Vuelo vuelo = vueloService.deleteVuelo(id);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<VueloDto> editVuelo(@PathVariable final Long id,
                                              @RequestBody final VueloDto vueloDto){
        Vuelo editedVuelo = vueloService.editVuelo(id, Vuelo.from(vueloDto));
        return new ResponseEntity<>(VueloDto.from(editedVuelo), HttpStatus.OK);
    }

    @PostMapping(value = "{idVuelo}/escalas/{idEscala}/add")
    public ResponseEntity<VueloDto> addEscalaToVuelo(@PathVariable final Long idVuelo,
                                                     @PathVariable final Long idEscala){
        Vuelo vuelo = vueloService.addEscalaToVuelo(idVuelo, idEscala);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    @PutMapping(value = "{idVuelo}/escalas/{idEscala}/remove")
    public ResponseEntity<VueloDto> removeEscalaToVuelo(@PathVariable final Long idVuelo,
                                                     @PathVariable final Long idEscala){
        Vuelo vuelo = vueloService.removeEscalaFromVuelo(idVuelo, idEscala);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    @PostMapping(value = "{idVuelo}/boleto/{idBoleto}/add")
    public ResponseEntity<VueloDto> addBoletoToVuelo(@PathVariable final Long idVuelo,
                                                     @PathVariable final Long idBoleto){
        Vuelo vuelo = vueloService.addBoletoToVuelo(idVuelo, idBoleto);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    @PutMapping(value = "{idVuelo}/boleto/{idBoleto}/remove")
    public ResponseEntity<VueloDto> removeBoletoToVuelo(@PathVariable final Long idVuelo,
                                                        @PathVariable final Long idBoleto){
        Vuelo vuelo = vueloService.removeEscalaFromVuelo(idVuelo, idBoleto);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }
}
