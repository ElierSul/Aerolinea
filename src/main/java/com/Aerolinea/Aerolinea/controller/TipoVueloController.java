package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.MedioPago;
import com.Aerolinea.Aerolinea.model.TipoVuelo;
import com.Aerolinea.Aerolinea.model.dto.MedioPagoDto;
import com.Aerolinea.Aerolinea.model.dto.TipoVueloDto;
import com.Aerolinea.Aerolinea.service.TipoVueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tipos")
public class TipoVueloController {

    private final TipoVueloService tipoVueloService;

    @Autowired
    public TipoVueloController(TipoVueloService tipoVueloService) {
        this.tipoVueloService = tipoVueloService;
    }

    @PostMapping
    public ResponseEntity<TipoVueloDto> addTipoVuelo(@RequestBody final TipoVueloDto tipoVueloDto){
        TipoVuelo tipoVuelo = tipoVueloService.addTipoVuelo(TipoVuelo.from(tipoVueloDto));
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TipoVueloDto>> getTiposVuelo(){
        List<TipoVuelo> tipoVuelos = tipoVueloService.getTiposVuelo();
        List<TipoVueloDto> tiposVueloDto = tipoVuelos.stream().map(TipoVueloDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(tiposVueloDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<TipoVueloDto> getTipoVuelo(@PathVariable final Long id){
        TipoVuelo tipoVuelo = tipoVueloService.getTipoVuelo(id);
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<TipoVueloDto> deleteTipoVuelo(@PathVariable final Long id){
        TipoVuelo tipoVuelo = tipoVueloService.deleteTipoVuelo(id);
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<TipoVueloDto> editTipoVuelo(@PathVariable final Long id,
                                                      @RequestBody final TipoVueloDto tipoVueloDto){
        TipoVuelo tipoVuelo = tipoVueloService.editTipoVuelo(id, TipoVuelo.from(tipoVueloDto));
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    @PostMapping(value = "{idTipo}/vuelos/{idVuelo}/add")
    public ResponseEntity<TipoVueloDto> addVueloToTipoVuelo(@PathVariable final Long idTipo,
                                                             @PathVariable final Long idVuelo){
        TipoVuelo tipoVuelo = tipoVueloService.addVueloToTipoVuelo(idTipo, idVuelo);
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    @PostMapping(value = "{idTipo}/vuelos/{idVuelos}/remove")
    public ResponseEntity<TipoVueloDto> removeVueloFromTipoVuelo(@PathVariable final Long idTipo,
                                                                  @PathVariable final Long idVuelo){
        TipoVuelo tipoVuelo = tipoVueloService.removeVueloFromTipoVuelo(idTipo, idVuelo);
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }
}
