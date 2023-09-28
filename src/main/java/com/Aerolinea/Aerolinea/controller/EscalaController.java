package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Escala;
import com.Aerolinea.Aerolinea.model.dto.EscalaDto;
import com.Aerolinea.Aerolinea.service.EscalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/escalas")
public class EscalaController {

    private final EscalaService escalaService;

    @Autowired
    public EscalaController(EscalaService escalaService) {
        this.escalaService = escalaService;
    }

    @PostMapping
    public ResponseEntity<EscalaDto> addEscala(@RequestBody final EscalaDto escalaDto){
        Escala escala = escalaService.addEscala(Escala.from(escalaDto));
        return new ResponseEntity<>(EscalaDto.from(escala), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EscalaDto>> getEscalas(){
        List<Escala> escalas = escalaService.getEscalas();
        List<EscalaDto> escalasDto = escalas.stream().map(EscalaDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(escalasDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<EscalaDto> getEscala(@PathVariable final Long id){
        Escala escala = escalaService.getEscala(id);
        return new ResponseEntity<>(EscalaDto.from(escala), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<EscalaDto> deleteEscala(@PathVariable final Long id){
        Escala escala = escalaService.deleteEscala(id);
        return new ResponseEntity<>(EscalaDto.from(escala), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<EscalaDto> editEscala(@PathVariable final Long id,
                                                @RequestBody final EscalaDto escalaDto){
        Escala editedEscala = escalaService.editEscala(id, Escala.from(escalaDto));
        return new ResponseEntity<>(EscalaDto.from(editedEscala), HttpStatus.OK);
    }

}
