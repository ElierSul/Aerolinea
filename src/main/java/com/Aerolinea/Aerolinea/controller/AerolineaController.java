package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Aerolinea;
import com.Aerolinea.Aerolinea.model.dto.AerolineaDto;
import com.Aerolinea.Aerolinea.service.AerolineaService;
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
@RequestMapping("/aerolineas")
public class AerolineaController {

    private final AerolineaService aerolineaService;

    @Autowired
    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }


    @Operation(summary = "Este Endpoint, permite a los usuarios crear una nueva aerolinea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: Secompleto la solicitud con exito, por tanto, " +
                    "se creo una nueva aerolinea satisfactoriamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AerolineaDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: Significa que la solicitud esta mal formulada o " +
                    "es incorrecta. Quizas algun dato erroneo en el JSON",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity: La solicitud es válida, pero no se " +
                    "puede procesar debido a un conflicto en los datos enviados.",
                    content = @Content) })

    @PostMapping("/crear")
    public ResponseEntity<AerolineaDto> addAerolinea(@RequestBody final AerolineaDto aerolineaDto){
        Aerolinea aerolinea = aerolineaService.addAerolinea(Aerolinea.from(aerolineaDto));
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios consultar todas las aerolineas que se encuentran " +
            "registradas en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito y se devuelve la información solicitada, " +
                    "respecto a la lista de aerolineas.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AerolineaDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la lista de aerolineas",
                    content = @Content) })

    @GetMapping("/listar")
    public ResponseEntity<List<AerolineaDto>> getAerolineas(){
        List<Aerolinea> aerolineas = aerolineaService.getAerolineas();
        List<AerolineaDto> aerolineasDto = aerolineas.stream().map(AerolineaDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(aerolineasDto, HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios buscar una aerolinea con base en su id unico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito y se devuelve la información solicitada, " +
                    "respecto a la aerolinea consultada.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AerolineaDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la aerolinea de la base de datos.",
                    content = @Content) })

    @GetMapping(value = "{id}")
    public ResponseEntity<AerolineaDto> getAerolinea(@PathVariable final Long id){
        Aerolinea aerolinea = aerolineaService.getAerolinea(id);
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios eliminar una aerolinea de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se elimino la aerolinea con el id especificado de " +
                    "la base de datos.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AerolineaDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la aerolinea que se desea eliminar de la base de datos.",
                    content = @Content) })

    @DeleteMapping(value = "{id}")
    public ResponseEntity<AerolineaDto> deleteAerolinea(@PathVariable final Long id){
        Aerolinea aerolinea = aerolineaService.deleteAerolinea(id);
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios editar una aerolinea especifica de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se edito de forma satisfactoria " +
                    "la aerolinea con el id especificado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AerolineaDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada. Podria ser un dato mal diligenciado.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la aerolinea que se desea editar de la base de datos.",
                    content = @Content)})

    @PutMapping(value = "{id}")
    public ResponseEntity<AerolineaDto> editAerolinea(@PathVariable final Long id,
                                                      @RequestBody final AerolineaDto aerolineaDto){
        Aerolinea aerolinea = aerolineaService.editAerolinea(id, Aerolinea.from(aerolineaDto));
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios asignar una aerolinea a un vuelo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se asocio la aerolinea con el vuelo deseado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AerolineaDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la aerolinea o el vuelo por su id de la base de datos.",
                    content = @Content)})

    @PostMapping(value = "{idAerolinea}/vuelos/{idVuelo}/add")
    public ResponseEntity<AerolineaDto> addAerolineaToVuelo(@PathVariable final Long idAerolinea,
                                                             @PathVariable final Long idVuelo){
        Aerolinea aerolinea = aerolineaService.addVueloToAerolinea(idAerolinea, idVuelo);
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios remover una asociacion entre un vuelo y una aerolinea.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se elimino la asociacion entre la aerolinea con el vuelo deseado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AerolineaDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la aerolinea o el vuelo por su id de la base de datos.",
                    content = @Content)})

    @PostMapping(value = "{idAerolinea}/vuelos/{idVuelo}/remove")
    public ResponseEntity<AerolineaDto> removeAerolineaFromVuelo(@PathVariable final Long idAerolinea,
                                                                  @PathVariable final Long idVuelo){
        Aerolinea aerolinea = aerolineaService.removeVueloFromAerolinea(idAerolinea, idVuelo);
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }
}
