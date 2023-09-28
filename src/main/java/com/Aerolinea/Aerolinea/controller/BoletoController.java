package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.dto.BoletoDto;
import com.Aerolinea.Aerolinea.service.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/boletos")
public class BoletoController {

    private final BoletoService boletoService;

    @Autowired
    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @PostMapping
    public ResponseEntity<BoletoDto> addBoleto(@RequestBody final BoletoDto boletoDto){
        Boleto boleto = boletoService.addBoleto(Boleto.from(boletoDto));
        return new ResponseEntity<>(BoletoDto.from(boleto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BoletoDto>> getBoletos(){
        List<Boleto> boletos = boletoService.getBoletos();
        List<BoletoDto> boletosDto = boletos.stream().map(BoletoDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(boletosDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<BoletoDto> getBoleto(@PathVariable final Long id){
        Boleto boleto = boletoService.getBoleto(id);
        return new ResponseEntity<>(BoletoDto.from(boleto), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<BoletoDto> deleteBoleto(@PathVariable final Long id){
        Boleto boleto = boletoService.deleteBoleto(id);
        return new ResponseEntity<>(BoletoDto.from(boleto), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<BoletoDto> editBoleto(@PathVariable final Long id,
                                                @RequestBody final BoletoDto boletoDto){
        Boleto editedBoleto = boletoService.editBoleto(id, Boleto.from(boletoDto));
        return new ResponseEntity<>(BoletoDto.from(editedBoleto), HttpStatus.OK);
    }
}
