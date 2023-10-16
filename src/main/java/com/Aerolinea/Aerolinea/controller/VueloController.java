package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Vuelo;
import com.Aerolinea.Aerolinea.model.dto.VueloDto;
import com.Aerolinea.Aerolinea.service.VueloService;
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
@RequestMapping("api/v1/vuelos")
@Api(tags = "MedioPago", description = "Controlador de medios de pago")
public class VueloController {

    private final VueloService vueloService;

    @Autowired
    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Registrar un vuelo", notes = "Se recibe por el body un objeto de VueloDto" +
            "y esta se registra en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo el registro correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping("/crearVuelo")
    public ResponseEntity<VueloDto> addVuelo(@RequestBody final VueloDto vueloDto){
        Vuelo vuelo = vueloService.addVuelo(Vuelo.from(vueloDto));
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Mostrar vuelos", notes = "Se retorna una lista de vuelos de VuelosDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se muestra la informacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping("/listarVuelos")
    public ResponseEntity<List<VueloDto>> getVuelos(){
        List<Vuelo> vuelos = vueloService.getVuelos();
        List<VueloDto> vueloDtos = vuelos.stream().map(VueloDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(vueloDtos, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Buscar un vuelo en especifico", notes = "Se retorna el vuelo asociado al ID suministrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la busqueda correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping("/buscarVuelo/{id}")
    public ResponseEntity<VueloDto> getVuelo(@PathVariable final Long id){
        Vuelo vuelo = vueloService.getVuelo(id);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Eliminar un vuelo", notes = "Se elimina el objeto de VueloDto asociado al ID suministrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la eliminacion de forma correcta"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @DeleteMapping(value = "/eliminarVueloPorId/{id}")
    public ResponseEntity<VueloDto> deleteVuelo(@PathVariable final Long id){
        Vuelo vuelo = vueloService.deleteVuelo(id);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Editar una vuelo", notes = "Se recibe en el body la informacion actualizada asociada " +
            "al ID suministrado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la actualizacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PutMapping(value = "/editarVuelo/{id}")
    public ResponseEntity<VueloDto> editVuelo(@PathVariable final Long id,
                                              @RequestBody final VueloDto vueloDto){
        Vuelo editedVuelo = vueloService.editVuelo(id, Vuelo.from(vueloDto));
        return new ResponseEntity<>(VueloDto.from(editedVuelo), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Asociar un vuelo a una escala", notes = "Se reciben los ID del vuelo respectivo y la escala " +
            "respectiva que se desean asociar.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la asociacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping(value = "/asociarEscalaVuelo/{idVuelo}/escalas/{idEscala}/add")
    public ResponseEntity<VueloDto> addEscalaToVuelo(@PathVariable final Long idVuelo,
                                                     @PathVariable final Long idEscala){
        Vuelo vuelo = vueloService.addEscalaToVuelo(idVuelo, idEscala);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Desasociar un vuelo de una escala", notes = "Se reciben los ID del vuelo respectivo y la escala " +
            "respectiva que se desean desasociar.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la desasociacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PutMapping(value = "/removerAsociacionEscalaVuelo/{idVuelo}/escalas/{idEscala}/remove")
    public ResponseEntity<VueloDto> removeEscalaToVuelo(@PathVariable final Long idVuelo,
                                                     @PathVariable final Long idEscala){
        Vuelo vuelo = vueloService.removeEscalaFromVuelo(idVuelo, idEscala);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Asociar un vuelo a un boleto", notes = "Se reciben los ID del vuelo respectivo y el boleto " +
            "respectivo que se desean asociar.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la asociacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping(value = "/asociarBoletoVuelo{idVuelo}/boleto/{idBoleto}/add")
    public ResponseEntity<VueloDto> addBoletoToVuelo(@PathVariable final Long idVuelo,
                                                     @PathVariable final Long idBoleto){
        Vuelo vuelo = vueloService.addBoletoToVuelo(idVuelo, idBoleto);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Desasociar un vuelo de un boleto", notes = "Se reciben los ID del vuelo respectivo y el boleto " +
            "respectiva que se desean desasociar.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la desasociacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PutMapping(value = "removerAsociacionBoletoVuelo{idVuelo}/boleto/{idBoleto}/remove")
    public ResponseEntity<VueloDto> removeBoletoToVuelo(@PathVariable final Long idVuelo,
                                                        @PathVariable final Long idBoleto){
        Vuelo vuelo = vueloService.removeEscalaFromVuelo(idVuelo, idBoleto);
        return new ResponseEntity<>(VueloDto.from(vuelo), HttpStatus.OK);
    }
}
