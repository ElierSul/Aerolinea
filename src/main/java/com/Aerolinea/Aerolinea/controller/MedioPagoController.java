package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.MedioPago;
import com.Aerolinea.Aerolinea.model.dto.MedioPagoDto;
import com.Aerolinea.Aerolinea.service.MedioPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mediosPago")
public class MedioPagoController {

    private final MedioPagoService medioPagoService;

    @Autowired
    public MedioPagoController(MedioPagoService medioPagoService) {
        this.medioPagoService = medioPagoService;
    }

    @PostMapping
    public ResponseEntity<MedioPagoDto> addMedioPago(@RequestBody final MedioPagoDto medioPagoDto){
        MedioPago medioPago = medioPagoService.addMedioPago(MedioPago.from(medioPagoDto));
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MedioPagoDto>> getMediosPago(){
        List<MedioPago> mediosPago = medioPagoService.getMediosPago();
        List<MedioPagoDto> medioPagoDtos = mediosPago.stream().map(MedioPagoDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(medioPagoDtos, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<MedioPagoDto> getMedioPago(@PathVariable final Long id){
        MedioPago medioPago = medioPagoService.getMedioPago(id);
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<MedioPagoDto> deleteMedioPago(@PathVariable final Long id){
        MedioPago medioPago = medioPagoService.deleteMedioPago(id);
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<MedioPagoDto> editMedioPago(@PathVariable final Long id,
                                                    @RequestBody final MedioPagoDto medioPagoDto){
        MedioPago medioPago = medioPagoService.editMedioPago(id, MedioPago.from(medioPagoDto));
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    @PostMapping(value = "{idMedioPago}/boletos/{idBoleto}/add")
    public ResponseEntity<MedioPagoDto> addBoletoToMedioPago(@PathVariable final Long idMedioPago,
                                                           @PathVariable final Long idBoleto){
        MedioPago medioPago = medioPagoService.addBoletoToMedioPago(idMedioPago, idBoleto);
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    @PostMapping(value = "{idMedioPago}/boletos/{idBoleto}/remove")
    public ResponseEntity<MedioPagoDto> removeBoletoToMedioPago(@PathVariable final Long idMedioPago,
                                                             @PathVariable final Long idBoleto){
        MedioPago medioPago = medioPagoService.removeBoletoFromMedioPago(idMedioPago, idBoleto);
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }
}
