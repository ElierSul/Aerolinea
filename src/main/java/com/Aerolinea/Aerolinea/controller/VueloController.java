package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Vuelo;
import com.Aerolinea.Aerolinea.model.dto.VueloDto;
import com.Aerolinea.Aerolinea.service.VueloService;
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
@RequestMapping("/vuelos")
public class VueloController {

    private final VueloService vueloService;

    @Autowired
    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios crear un nuevo vuelo. Los unicos datos que debe proporcionar " +
            "el usuario son: lugaresDisponibles y costo. Los demas datos se generan automaticamente al realizar las asignaciones " +
            "pertienentes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: Secompleto la solicitud con exito, por tanto, " +
                    "se creo un nuevo vuelo satisfactoriamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: Significa que la solicitud esta mal formulada o " +
                    "es incorrecta. Quizas algun dato erroneo en el JSON",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity: La solicitud es válida, pero no se " +
                    "puede procesar debido a un conflicto en los datos enviados.",
                    content = @Content) })

    @PostMapping("/crear")
    public ResponseEntity<VueloDto> addVuelo(@RequestBody final VueloDto vueloDto){
        Vuelo vuelo = vueloService.addVuelo(Vuelo.from(vueloDto));
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios consultar todos los vuelos que se encuentran " +
            "registrados en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito y se devuelve la información solicitada, " +
                    "respecto a la lista de vuelos.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la lista de aerolineas",
                    content = @Content) })

    @GetMapping("/listar")
    public ResponseEntity<List<VueloDto>> getVuelos(){
        List<Vuelo> vuelos = vueloService.getVuelos();
        List<VueloDto> vueloDtos = vuelos.stream().map(VueloDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(vueloDtos, HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios buscar un vuelo con base en su id unico. El usuario debe diligenciar el id del " +
            "vuelo que desea buscar. En caso de no existir el vuelo que se esta buscando se genera una excepcion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito y se devuelve la información solicitada, " +
                    "respecto al vuelo consultado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el vuelo de la base de datos.",
                    content = @Content) })

    @GetMapping(value = "{id}")
    public ResponseEntity<VueloDto> getVuelo(@PathVariable final Long id){
        Vuelo vuelo = vueloService.getVuelo(id);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios eliminar un vuelo de la base de datos. El usuario debe suministrar el " +
            "id del vuelo que desea eliminar de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se elimino el vuelo con el id especificado de " +
                    "la base de datos.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el vuelo que se desea eliminar de la base de datos.",
                    content = @Content) })

    @DeleteMapping(value = "{id}")
    public ResponseEntity<VueloDto> deleteVuelo(@PathVariable final Long id){
        Vuelo vuelo = vueloService.deleteVuelo(id);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios editar un vuelo especifico de la base de datos. El usuario debe suministrar " +
            "el id del vuelo que desea editar. Asi como los datos en el JSON, en este caso los lugares disponibles y el costo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se edito de forma satisfactoria " +
                    "el vuelo con el id especificado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada. Podria ser un dato mal diligenciado.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el vuelo que se desea editar de la base de datos.",
                    content = @Content)})

    @PutMapping(value = "{id}")
    public ResponseEntity<VueloDto> editVuelo(@PathVariable final Long id,
                                              @RequestBody final VueloDto vueloDto){
        Vuelo editedVuelo = vueloService.editVuelo(id, Vuelo.from(vueloDto));
        return new ResponseEntity<>(VueloDto.from(editedVuelo), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios asociar una escala a un vuelo. El usuario debe de suministrar el " +
            "id del vuelo y el id de la escala respectivamente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se asocio la escala al vuelo deseado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la escala o el vuelo por su id de la base de datos.",
                    content = @Content)})

    @PostMapping(value = "{idVuelo}/escalas/{idEscala}/add")
    public ResponseEntity<VueloDto> addEscalaToVuelo(@PathVariable final Long idVuelo,
                                                     @PathVariable final Long idEscala){
        Vuelo vuelo = vueloService.addEscalaToVuelo(idVuelo, idEscala);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios eliminar una asociacion entre un vuelo y una escala. El usuario debe de suministrar " +
            "el id del vuelo y el id de la escala respectivamente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se elimino la asociacion entre la escala y " +
                    "el vuelo deseado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la aerolineael boleto o el vuelo por su id de la base de datos.",
                    content = @Content)})

    @PostMapping(value = "{idVuelo}/escalas/{idEscala}/remove")
    public ResponseEntity<VueloDto> removeEscalaToVuelo(@PathVariable final Long idVuelo,
                                                     @PathVariable final Long idEscala){
        Vuelo vuelo = vueloService.removeEscalaFromVuelo(idVuelo, idEscala);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios asignar un vuelo a un boleto. Adicionalmente se valida " +
            "internamente que aun existan lugares disponibles en el vuelo, en caso que no existan lugares disponibles, " +
            "se lanza una excepcion. El usuario debe de suministrar el id del vuelo y el id del boleto respectivamente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se asocio el vuelo con el boleto deseado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada. Es " +
                    "posible que ya no existan lugares disponibles.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el boleto o el vuelo por su id de la base de datos.",
                    content = @Content)})

    @PostMapping(value = "{idVuelo}/boleto/{idBoleto}/add")
    public ResponseEntity<VueloDto> addBoletoToVuelo(@PathVariable final Long idVuelo,
                                                     @PathVariable final Long idBoleto){
        Vuelo vuelo = vueloService.addBoletoToVuelo(idVuelo, idBoleto);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios eliminar una asociacion entre un vuelo y un boleto. El usuario debe de suministrar el " +
            "id del vuelo y el id del boleto respectivamente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se elimino la asociacion entre el " +
                    "boleto y el vuelo deseado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VueloDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la aerolineael boleto o el vuelo por su id de la base de datos.",
                    content = @Content)})

    @PostMapping(value = "{idVuelo}/boleto/{idBoleto}/remove")
    public ResponseEntity<VueloDto> removeBoletoToVuelo(@PathVariable final Long idVuelo,
                                                        @PathVariable final Long idBoleto){
        Vuelo vuelo = vueloService.removeBoletoToVuelo(idVuelo, idBoleto);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

}
