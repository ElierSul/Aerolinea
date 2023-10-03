package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Aerolinea;
import com.Aerolinea.Aerolinea.model.MedioPago;
import com.Aerolinea.Aerolinea.model.dto.AerolineaDto;
import com.Aerolinea.Aerolinea.model.dto.MedioPagoDto;
import com.Aerolinea.Aerolinea.service.AerolineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aerolineas")
public class AerolineaController {

    private final AerolineaService aerolineaService;

    @Autowired
    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }

    @PostMapping
    public ResponseEntity<AerolineaDto> addAerolinea(@RequestBody final AerolineaDto aerolineaDto){
        Aerolinea aerolinea = aerolineaService.addAerolinea(Aerolinea.from(aerolineaDto));
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AerolineaDto>> getAerolineas(){
        List<Aerolinea> aerolineas = aerolineaService.getAerolineas();
        List<AerolineaDto> aerolineasDto = aerolineas.stream().map(AerolineaDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(aerolineasDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<AerolineaDto> getAerolinea(@PathVariable final Long id){
        Aerolinea aerolinea = aerolineaService.getAerolinea(id);
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<AerolineaDto> deleteAerolinea(@PathVariable final Long id){
        Aerolinea aerolinea = aerolineaService.deleteAerolinea(id);
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<AerolineaDto> editAerolinea(@PathVariable final Long id,
                                                      @RequestBody final AerolineaDto aerolineaDto){
        Aerolinea aerolinea = aerolineaService.editAerolinea(id, Aerolinea.from(aerolineaDto));
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    @PostMapping(value = "{idAerolinea}/vuelos/{idVuelo}/add")
    public ResponseEntity<AerolineaDto> addAerolineaToVuelo(@PathVariable final Long idAerolinea,
                                                             @PathVariable final Long idVuelo){
        Aerolinea aerolinea = aerolineaService.addVueloToAerolinea(idAerolinea, idVuelo);
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    @PostMapping(value = "{idAerolinea}/vuelos/{idVuelo}/remove")
    public ResponseEntity<AerolineaDto> removeAerolineaFromVuelo(@PathVariable final Long idAerolinea,
                                                                  @PathVariable final Long idVuelo){
        Aerolinea aerolinea = aerolineaService.removeVueloFromAerolinea(idAerolinea, idVuelo);
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }
}
