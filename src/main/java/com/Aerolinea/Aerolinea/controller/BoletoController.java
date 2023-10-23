package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.dto.BoletoDto;
import com.Aerolinea.Aerolinea.service.BoletoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Este Endpoint permite Agregar un nuevo Boleto. En este endpoint no es necesario que el " +
            "usuario realice ningun cambio en el JSON, ya que todas las asignaciones se realizan de forma logica en " +
            "la capa service.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "¡La solicitud se ha completado!",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BoletoDto.class)) }),
            @ApiResponse(responseCode = "201", description = "¡La solicitud ha creado un Boleto con éxito!.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boleto.class)) }),
            @ApiResponse(responseCode = "400", description = "Error por solicitud mal formada, Boleto no creado",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "La solicitud no procesó debido a un conflicto de datos en la petición, Boleto no creado.",
                    content = @Content) })

    @PostMapping("/crear")
    public ResponseEntity<BoletoDto> addBoleto(@RequestBody final BoletoDto boletoDto){
        Boleto boleto = boletoService.addBoleto(Boleto.from(boletoDto));
        return new ResponseEntity<>(BoletoDto.from(boleto), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint nos permite listar todos los Boletos registrados en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "¡Solicitud con éxito!. Boletos existentes.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BoletoDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Solicitud inconcreta",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Usted no tiene autorización para esta solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No existen Boletos",
                    content = @Content) })

    @GetMapping("/listar")
    public ResponseEntity<List<BoletoDto>> getBoletos(){
        List<Boleto> boletos = boletoService.getBoletos();
        List<BoletoDto> boletosDto = boletos.stream().map(BoletoDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(boletosDto, HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint nos permite buscar un Boleto con base en su id unico. El usuario debe suministrar el id del boleto que desea buscar. " +
            "En caso de no encontrarlo en la base de datos se lanza una excepcion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "¡Solicitud con éxito!. Boleto existente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BoletoDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Solicitud inconcreta",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Usted no tiene autorización para esta solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No existe Boleto",
                    content = @Content) })

    @GetMapping(value = "{id}")
    public ResponseEntity<BoletoDto> getBoleto(@PathVariable final Long id){
        Boleto boleto = boletoService.getBoleto(id);
        return new ResponseEntity<>(BoletoDto.from(boleto), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint nos permite Eliminar un Boleto. El usuario debe de suministrar el id del boleto que desea eliminar de " +
            "la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "¡Solicitud con éxito!. Boleto eliminado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BoletoDto.class)) }),
            @ApiResponse(responseCode = "204", description = "Solicitud con éxito, pero sin contenido.",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Solicitud inconcreta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No existe Boleto",
                    content = @Content) })
    @DeleteMapping(value = "{id}")
    public ResponseEntity<BoletoDto> deleteBoleto(@PathVariable final Long id){
        Boleto boleto = boletoService.deleteBoleto(id);
        return new ResponseEntity<>(BoletoDto.from(boleto), HttpStatus.OK);
    }

}
