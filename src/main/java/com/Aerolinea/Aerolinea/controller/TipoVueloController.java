package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.MedioPago;
import com.Aerolinea.Aerolinea.model.TipoVuelo;
import com.Aerolinea.Aerolinea.model.dto.MedioPagoDto;
import com.Aerolinea.Aerolinea.model.dto.TipoVueloDto;
import com.Aerolinea.Aerolinea.service.TipoVueloService;
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
@RequestMapping("api/v1/tipos")
@Api(tags = "TipoVuelo", description = "Controlador de tipos de vuelo")
public class TipoVueloController {

    private final TipoVueloService tipoVueloService;

    @Autowired
    public TipoVueloController(TipoVueloService tipoVueloService) {
        this.tipoVueloService = tipoVueloService;
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Registrar un tipo de vuelo", notes = "Se recibe por el body un objeto de TipoVueloDto" +
            " y esta se registra en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo el registro correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping("/crearTipoVuelo")
    public ResponseEntity<TipoVueloDto> addTipoVuelo(@RequestBody final TipoVueloDto tipoVueloDto){
        TipoVuelo tipoVuelo = tipoVueloService.addTipoVuelo(TipoVuelo.from(tipoVueloDto));
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Mostrar tipos de vuelo", notes = "Se retorna una lista de tipos de vuelo de TipoVueloDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se muestra la informacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping("/listarTipoVuelo")
    public ResponseEntity<List<TipoVueloDto>> getTiposVuelo(){
        List<TipoVuelo> tipoVuelos = tipoVueloService.getTiposVuelo();
        List<TipoVueloDto> tiposVueloDto = tipoVuelos.stream().map(TipoVueloDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(tiposVueloDto, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Buscar un tipo de vuelo en especifico", notes = "Se retorna el tipo de vuelo asociado al ID suministrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la busqueda correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping(value = "/buscarTipoVueloPorId/{id}")
    public ResponseEntity<TipoVueloDto> getTipoVuelo(@PathVariable final Long id){
        TipoVuelo tipoVuelo = tipoVueloService.getTipoVuelo(id);
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Eliminar un tipo de vuelo", notes = "Se elimina el objeto de TipoVueloDto asociado al ID suministrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la eliminacion de forma correcta"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @DeleteMapping(value = "/eliminarTipoVueloPorId/{id}")
    public ResponseEntity<TipoVueloDto> deleteTipoVuelo(@PathVariable final Long id){
        TipoVuelo tipoVuelo = tipoVueloService.deleteTipoVuelo(id);
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Editar un tipo de vuelo", notes = "Se recibe en el body la informacion actualizada asociada " +
            "al ID suministrado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la actualizacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PutMapping(value = "/editarTipoVuelo/{id}")
    public ResponseEntity<TipoVueloDto> editTipoVuelo(@PathVariable final Long id,
                                                      @RequestBody final TipoVueloDto tipoVueloDto){
        TipoVuelo tipoVuelo = tipoVueloService.editTipoVuelo(id, TipoVuelo.from(tipoVueloDto));
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Asociar un tipo de vuelo a un vuelo", notes = "Se reciben los ID del tipo de vuelo respectivo y el vuelo " +
            "respectivo que se desean asociar.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la asociacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping(value = "/asociarTipoVuelo/{idTipo}/vuelos/{idVuelo}/add")
    public ResponseEntity<TipoVueloDto> addVueloToTipoVuelo(@PathVariable final Long idTipo,
                                                             @PathVariable final Long idVuelo){
        TipoVuelo tipoVuelo = tipoVueloService.addVueloToTipoVuelo(idTipo, idVuelo);
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Desasociar un tipo de vuelo de un vuelo", notes = "Se reciben los ID del tipo de vuelo respectivo y el vuelo " +
            "respectivo que se desean desasociar.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la desasociacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping(value = "/eliminarAsociacion/{idTipo}/vuelos/{idVuelos}/remove")
    public ResponseEntity<TipoVueloDto> removeVueloFromTipoVuelo(@PathVariable final Long idTipo,
                                                                  @PathVariable final Long idVuelo){
        TipoVuelo tipoVuelo = tipoVueloService.removeVueloFromTipoVuelo(idTipo, idVuelo);
        return new ResponseEntity<>(TipoVueloDto.from(tipoVuelo), HttpStatus.OK);
    }
}
