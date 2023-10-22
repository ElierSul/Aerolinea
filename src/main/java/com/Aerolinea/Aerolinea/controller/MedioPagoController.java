package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.MedioPago;
import com.Aerolinea.Aerolinea.model.dto.MedioPagoDto;
import com.Aerolinea.Aerolinea.service.MedioPagoService;
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
@RequestMapping("/mediosPago")
public class MedioPagoController {

    private final MedioPagoService medioPagoService;

    @Autowired
    public MedioPagoController(MedioPagoService medioPagoService) {
        this.medioPagoService = medioPagoService;
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios crear un nuevo medio de pago de tipo PSE, tarjeta de credito" +
            " o tarjeta debito.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: Secompleto la solicitud con exito, por tanto, " +
                    "se creo un nuevo medio de pago.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MedioPagoDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: Significa que la solicitud esta mal formulada o " +
                    "es incorrecta. Quizas algun dato erroneo en el JSON",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity: La solicitud es válida, pero no se " +
                    "puede procesar debido a un conflicto en los datos enviados.",
                    content = @Content) })

    @PostMapping("/crear")
    public ResponseEntity<MedioPagoDto> addMedioPago(@RequestBody final MedioPagoDto medioPagoDto){
        MedioPago medioPago = medioPagoService.addMedioPago(MedioPago.from(medioPagoDto));
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios consultar medios de pago que se encuentran " +
            "registrados en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito y se devuelve la información solicitada, " +
                    "respecto a la lista de medios de pago.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MedioPagoDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la lista de aerolineas",
                    content = @Content) })

    @GetMapping("/listar")
    public ResponseEntity<List<MedioPagoDto>> getMediosPago(){
        List<MedioPago> mediosPago = medioPagoService.getMediosPago();
        List<MedioPagoDto> medioPagoDtos = mediosPago.stream().map(MedioPagoDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(medioPagoDtos, HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios buscar un medio de pago con base en su id unico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito y se devuelve la información solicitada, " +
                    "respecto al medio de pago consultado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MedioPagoDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la aerolinea de la base de datos.",
                    content = @Content) })

    @GetMapping(value = "{id}")
    public ResponseEntity<MedioPagoDto> getMedioPago(@PathVariable final Long id){
        MedioPago medioPago = medioPagoService.getMedioPago(id);
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios eliminar un medio de pago de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se elimino el " +
                    "medio de pago con el id especificado de la base de datos.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MedioPagoDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el medio de pago que se desea eliminar de la base de datos.",
                    content = @Content) })

    @DeleteMapping(value = "{id}")
    public ResponseEntity<MedioPagoDto> deleteMedioPago(@PathVariable final Long id){
        MedioPago medioPago = medioPagoService.deleteMedioPago(id);
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios editar un medio de pago especifico de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se edito de forma satisfactoria " +
                    "el medio de pago con el id especificado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MedioPagoDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada. Podria ser un dato mal diligenciado.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el medio de pago que se desea editar de la base de datos.",
                    content = @Content)})

    @PutMapping(value = "{id}")
    public ResponseEntity<MedioPagoDto> editMedioPago(@PathVariable final Long id,
                                                    @RequestBody final MedioPagoDto medioPagoDto){
        MedioPago medioPago = medioPagoService.editMedioPago(id, MedioPago.from(medioPagoDto));
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios asignar un medio de pago a un boleto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se asocio el medio de pago " +
                    " con el boleto deseado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MedioPagoDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el medio de pago o el boleto por su id de la base de datos.",
                    content = @Content)})

    @PostMapping(value = "{idMedioPago}/boletos/{idBoleto}/add")
    public ResponseEntity<MedioPagoDto> addBoletoToMedioPago(@PathVariable final Long idMedioPago,
                                                           @PathVariable final Long idBoleto){
        MedioPago medioPago = medioPagoService.addBoletoToMedioPago(idMedioPago, idBoleto);
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite a los usuarios remover una asociacion entre un medio de pago y un boleto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se elimino la asociacion entre " +
                    "el medio de pago y el boleto.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MedioPagoDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el medio de pago o el boleto por su id de la base de datos.",
                    content = @Content)})

    @PostMapping(value = "{idMedioPago}/boletos/{idBoleto}/remove")
    public ResponseEntity<MedioPagoDto> removeBoletoFromMedioPago(@PathVariable final Long idMedioPago,
                                                             @PathVariable final Long idBoleto){
        MedioPago medioPago = medioPagoService.removeBoletoFromMedioPago(idMedioPago, idBoleto);
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }
}
