package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Traslado;
import com.Aerolinea.Aerolinea.model.dto.EscalaDto;
import com.Aerolinea.Aerolinea.model.dto.TrasladoDto;
import com.Aerolinea.Aerolinea.service.TrasladoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/traslados")
public class TrasladoController {

    private final TrasladoService trasladoService;

    @Autowired
    public TrasladoController(TrasladoService trasladoService) {
        this.trasladoService = trasladoService;
    }

    @PostMapping
    public ResponseEntity<TrasladoDto> addTraslado(@RequestBody final TrasladoDto trasladoDto){
        Traslado traslado = trasladoService.addTraslado(Traslado.from(trasladoDto));
        return new ResponseEntity<>(TrasladoDto.from(traslado), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TrasladoDto>> getTraslados(){
        List<Traslado> traslados = trasladoService.getTraslados();
        List<TrasladoDto> trasladosDto = traslados.stream().map(TrasladoDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(trasladosDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<TrasladoDto> getTraslado(@PathVariable final Long id){
        Traslado traslado = trasladoService.getTraslado(id);
        return new ResponseEntity<>(TrasladoDto.from(traslado), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<TrasladoDto> deleteTraslado(@PathVariable final Long id){
        Traslado traslado = trasladoService.deleteTraslado(id);
        return new ResponseEntity<>(TrasladoDto.from(traslado), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<TrasladoDto> editTraslado(@PathVariable final Long id,
                                                    @RequestBody final TrasladoDto trasladoDto){
        Traslado traslado = trasladoService.editTraslado(id, Traslado.from(trasladoDto));
        return new ResponseEntity<>(TrasladoDto.from(traslado), HttpStatus.OK);
    }

    @PostMapping(value = "{idTraslado}/escalas/{idEscala}/add")
    public ResponseEntity<TrasladoDto> addEscalaToTraslado(@PathVariable final Long idTraslado,
                                                           @PathVariable final Long idEscala){
        Traslado traslado = trasladoService.addEscalaToTraslado(idTraslado, idEscala);
        return new ResponseEntity<>(TrasladoDto.from(traslado), HttpStatus.OK);
    }

    @DeleteMapping(value = "{idTraslado}/escalas/{idEscala}/remove")
    public ResponseEntity<TrasladoDto> removeEscalaFromTraslado(@PathVariable final Long idTraslado,
                                                           @PathVariable final Long idEscala){
        Traslado traslado = trasladoService.removeEscalaToTraslado(idTraslado, idEscala);
        return new ResponseEntity<>(TrasladoDto.from(traslado), HttpStatus.OK);
    }
}
