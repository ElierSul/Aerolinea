package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Escala;
import com.Aerolinea.Aerolinea.model.Vuelo;
import com.Aerolinea.Aerolinea.model.dto.EscalaDto;
import com.Aerolinea.Aerolinea.model.dto.VueloDto;
import com.Aerolinea.Aerolinea.service.EscalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @Operation(summary = "Este Endpoint, permite a los usuarios crear una nueva escala.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: Secompleto la solicitud con exito, por tanto, " +
                    "se creo una nueva escala satisfactoriamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EscalaDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: Significa que la solicitud esta mal formulada o " +
                    "es incorrecta. Quizas algun dato erroneo en el JSON",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity: La solicitud es válida, pero no se " +
                    "puede procesar debido a un conflicto en los datos enviados.",
                    content = @Content) })

    @PostMapping("/crear")
    public ResponseEntity<EscalaDto> addEscala(@RequestBody final EscalaDto escalaDto){
        Escala escala = escalaService.addEscala(Escala.from(escalaDto));
        return new ResponseEntity<>(EscalaDto.from(escala), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios consultar todas los escalas que se encuentran " +
            "registrados en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito y se devuelve la información solicitada, " +
                    "respecto a la lista de escalas.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EscalaDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la lista de escalas",
                    content = @Content) })

    @GetMapping("/listar")
    public ResponseEntity<List<EscalaDto>> getEscalas(){
        List<Escala> escalas = escalaService.getEscalas();
        List<EscalaDto> escalaDtos = escalas.stream().map(EscalaDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(escalaDtos, HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios buscar una escala con base en su id unico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito y se devuelve la información solicitada, " +
                    "respecto a la escala consultada.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EscalaDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la escala de la base de datos.",
                    content = @Content) })

    @GetMapping(value = "{id}")
    public ResponseEntity<EscalaDto> getEscala(@PathVariable final Long id){
        Escala escala = escalaService.getEscala(id);
        return new ResponseEntity<>(EscalaDto.from(escala), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios eliminar una escala de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se elimino la escala con el id especificado de " +
                    "la base de datos.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EscalaDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la escala que se desea eliminar de la base de datos.",
                    content = @Content) })

    @DeleteMapping(value = "{id}")
    public ResponseEntity<EscalaDto> deleteEscala(@PathVariable final Long id){
        Escala escala = escalaService.deleteEscala(id);
        return new ResponseEntity<>(EscalaDto.from(escala), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios editar un vuelo especifico de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se edito de forma satisfactoria " +
                    "el vuelo con el id especificado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EscalaDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada. Podria ser un dato mal diligenciado.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el vuelo que se desea editar de la base de datos.",
                    content = @Content)})

    @PutMapping(value = "{id}")
    public ResponseEntity<EscalaDto> editEscala(@PathVariable final Long id,
                                                  @RequestBody final EscalaDto escalaDto){
        Escala escala = escalaService.editEscala(id, Escala.from(escalaDto));
        return new ResponseEntity<>(EscalaDto.from(escala), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios consultar todos vuelos que cumplan con los requerimientos  de ciudad de origen, " +
            "ciudad de destino y fecha de partida de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se muestra la lista de vuelos que cumplen " +
                    "con los criterios de busqueda.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EscalaDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada. Podria ser un dato mal diligenciado.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar los vuelos que cumplen los criterios de busqueda.",
                    content = @Content)})

    @GetMapping("/vuelosDisponiblesEntreEscalas")
    public ResponseEntity<List<VueloDto>> getVuelosDisponiblesEntreEscalas(
            @RequestParam("ciudadOrigen") String ciudadOrigen,
            @RequestParam("ciudadDestino") String ciudadDestino,
            @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        List<Vuelo> vuelos = escalaService.getVuelosDisponiblesEntreTraslados(ciudadOrigen, ciudadDestino, fecha);
        List<VueloDto> vueloDtos = vuelos.stream().map(VueloDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(vueloDtos, HttpStatus.OK);
    }

}
