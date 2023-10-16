package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.MedioPago;
import com.Aerolinea.Aerolinea.model.dto.MedioPagoDto;
import com.Aerolinea.Aerolinea.service.MedioPagoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/mediosPago")
@Api(tags = "MedioPago", description = "Controlador de medios de pago")
public class MedioPagoController {

    private final MedioPagoService medioPagoService;

    @Autowired
    public MedioPagoController(MedioPagoService medioPagoService) {
        this.medioPagoService = medioPagoService;
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Registrar un medio de pago", notes = "Se recibe por el body un objeto de MedioPagoDto" +
            "y esta se registra en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo el registro correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping("/crearMedioPago")
    public ResponseEntity<MedioPagoDto> addMedioPago(@RequestBody final MedioPagoDto medioPagoDto){
        MedioPago medioPago = medioPagoService.addMedioPago(MedioPago.from(medioPagoDto));
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Mostrar medios de pago", notes = "Se retorna una lista de medios de pago de MedioPagoDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se muestra la informacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping("/listarMediosPago")
    public ResponseEntity<List<MedioPagoDto>> getMediosPago(){
        List<MedioPago> mediosPago = medioPagoService.getMediosPago();
        List<MedioPagoDto> medioPagoDtos = mediosPago.stream().map(MedioPagoDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(medioPagoDtos, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Buscar un medio de pago en especifico", notes = "Se retorna el medio de pago asociado al ID suministrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la busqueda correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping(value = "/buscarMedioPagoPorId/{id}")
    public ResponseEntity<MedioPagoDto> getMedioPago(@PathVariable final Long id){
        MedioPago medioPago = medioPagoService.getMedioPago(id);
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Eliminar un medio de pago", notes = "Se elimina el objeto de MedioPagoDto asociado al ID suministrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la eliminacion de forma correcta"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @DeleteMapping(value = "/eliminarMedioPagoPorId/{id}")
    public ResponseEntity<MedioPagoDto> deleteMedioPago(@PathVariable final Long id){
        MedioPago medioPago = medioPagoService.deleteMedioPago(id);
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Editar una medio de pago", notes = "Se recibe en el body la informacion actualizada asociada " +
            "al ID suministrado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la actualizacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PutMapping(value = "/editarMedioPago/{id}")
    public ResponseEntity<MedioPagoDto> editMedioPago(@PathVariable final Long id,
                                                    @RequestBody final MedioPagoDto medioPagoDto){
        MedioPago medioPago = medioPagoService.editMedioPago(id, MedioPago.from(medioPagoDto));
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Asociar un medio de pago a un boleto", notes = "Se reciben los ID del medio de pago respectivo y el boleto " +
            "respectivo que se desean asociar.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la asociacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping(value = "/asociarMedioPagoBoleto/{idMedioPago}/boletos/{idBoleto}/add")
    public ResponseEntity<MedioPagoDto> addBoletoToMedioPago(@PathVariable final Long idMedioPago,
                                                           @PathVariable final Long idBoleto){
        MedioPago medioPago = medioPagoService.addBoletoToMedioPago(idMedioPago, idBoleto);
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Desasociar un medio de pago de un boleto", notes = "Se reciben los ID del medio de pago respectivo y el boleto " +
            "respectiva que se desean desasociar.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la desasociacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping(value = "removerAsociacion/{idMedioPago}/boletos/{idBoleto}/remove")
    public ResponseEntity<MedioPagoDto> removeBoletoFromMedioPago(@PathVariable final Long idMedioPago,
                                                             @PathVariable final Long idBoleto){
        MedioPago medioPago = medioPagoService.removeBoletoFromMedioPago(idMedioPago, idBoleto);
        return new ResponseEntity<>(MedioPagoDto.from(medioPago), HttpStatus.OK);
    }
}
