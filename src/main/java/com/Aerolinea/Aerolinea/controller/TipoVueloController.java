package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.TipoVuelo;
import com.Aerolinea.Aerolinea.model.dto.MedioPagoDto;
import com.Aerolinea.Aerolinea.model.dto.TipoVueloDto;
import com.Aerolinea.Aerolinea.service.TipoVueloService;
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
@RequestMapping("/tipos")
public class TipoVueloController {

    private final TipoVueloService tipoVueloService;

    @Autowired
    public TipoVueloController(TipoVueloService tipoVueloService) {
        this.tipoVueloService = tipoVueloService;
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios crear un nuevo tipo de vuelo, bien sea publico o privado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: Secompleto la solicitud con exito, por tanto, " +
                    "se creo un nuevo tipo de vuelo.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoVueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: Significa que la solicitud esta mal formulada o " +
                    "es incorrecta. Quizas algun dato erroneo en el JSON",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity: La solicitud es válida, pero no se " +
                    "puede procesar debido a un conflicto en los datos enviados.",
                    content = @Content) })

    @PostMapping("/crear")
    public ResponseEntity<TipoVueloDto> addTipoVuelo(@RequestBody final TipoVueloDto tipoVueloDto){
        TipoVuelo tipoVuelo = tipoVueloService.addTipoVuelo(TipoVuelo.from(tipoVueloDto));
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios consultar todos los tipos de vuelo que se encuentran " +
            "registrados en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito y se devuelve la información solicitada, " +
                    "respecto a la lista de tipos de vuelo.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoVueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la lista de tipos de vuelos",
                    content = @Content) })

    @GetMapping("/listar")
    public ResponseEntity<List<TipoVueloDto>> getTiposVuelo(){
        List<TipoVuelo> tipoVuelos = tipoVueloService.getTiposVuelo();
        List<TipoVueloDto> tiposVueloDto = tipoVuelos.stream().map(TipoVueloDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(tiposVueloDto, HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios buscar un tipo de vuelo con base en su id unico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito y se devuelve la información solicitada, " +
                    "respecto al tipo de vuelo consultado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoVueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la aerolinea de la base de datos.",
                    content = @Content) })

    @GetMapping(value = "{id}")
    public ResponseEntity<TipoVueloDto> getTipoVuelo(@PathVariable final Long id){
        TipoVuelo tipoVuelo = tipoVueloService.getTipoVuelo(id);
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios eliminar un tipo de vuelo de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se elimino el " +
                    "tipo de vuelo con el id especificado de la base de datos.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MedioPagoDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el tipo de vuelo que se desea eliminar de la base de datos.",
                    content = @Content) })

    @DeleteMapping(value = "{id}")
    public ResponseEntity<TipoVueloDto> deleteTipoVuelo(@PathVariable final Long id){
        TipoVuelo tipoVuelo = tipoVueloService.deleteTipoVuelo(id);
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios editar un tipo de vuelo especifico de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se edito de forma satisfactoria " +
                    "el tipo de vuelo con el id especificado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoVueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada. Podria ser un dato mal diligenciado.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el tipo de vuelo que se desea editar de la base de datos.",
                    content = @Content)})

    @PutMapping(value = "{id}")
    public ResponseEntity<TipoVueloDto> editTipoVuelo(@PathVariable final Long id,
                                                      @RequestBody final TipoVueloDto tipoVueloDto){
        TipoVuelo tipoVuelo = tipoVueloService.editTipoVuelo(id, TipoVuelo.from(tipoVueloDto));
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios asignar un tipo de vuelo a un vuelo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se asocio el tipo de vuelo " +
                    " con el vuelo deseado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoVueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el tipo de vuelo o el vuelo por su id de la base de datos.",
                    content = @Content)})

    @PostMapping(value = "{idTipo}/vuelos/{idVuelo}/add")
    public ResponseEntity<TipoVueloDto> addVueloToTipoVuelo(@PathVariable final Long idTipo,
                                                             @PathVariable final Long idVuelo){
        TipoVuelo tipoVuelo = tipoVueloService.addVueloToTipoVuelo(idTipo, idVuelo);
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios remover una asociacion entre un tipo de vuelo y un vuelo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se elimino la asociacion entre " +
                    "el tipo de vuelo y el vuelo.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoVueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el tipo de vuelo o el vuelo por su id de la base de datos.",
                    content = @Content)})

    @PostMapping(value = "{idTipo}/vuelos/{idVuelos}/remove")
    public ResponseEntity<TipoVueloDto> removeVueloFromTipoVuelo(@PathVariable final Long idTipo,
                                                                  @PathVariable final Long idVuelo){
        TipoVuelo tipoVuelo = tipoVueloService.removeVueloFromTipoVuelo(idTipo, idVuelo);
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }
}
