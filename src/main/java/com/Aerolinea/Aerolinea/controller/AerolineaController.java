package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Aerolinea;
import com.Aerolinea.Aerolinea.model.dto.AerolineaDto;
import com.Aerolinea.Aerolinea.service.AerolineaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/aerolineas")
@Api(tags = "Aerolineas", description = "Controlador de aerolinea")
public class AerolineaController {

    private final AerolineaService aerolineaService;

    @Autowired
    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Registrar una aerolinea", notes = "Se recibe por el body un objeto de AerolineaDto" +
            "y esta se registra en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo el registro correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping("/crearAerolinea")
    public ResponseEntity<AerolineaDto> addAerolinea(@RequestBody final AerolineaDto aerolineaDto){
        Aerolinea aerolinea = aerolineaService.addAerolinea(Aerolinea.from(aerolineaDto));
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Mostrar aerolineas", notes = "Se retorna una lista de aerolineas de AerolineaDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se muestra la informacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping("/listarAerolineas")
    public ResponseEntity<List<AerolineaDto>> getAerolineas(){
        List<Aerolinea> aerolineas = aerolineaService.getAerolineas();
        List<AerolineaDto> aerolineasDto = aerolineas.stream().map(AerolineaDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(aerolineasDto, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Buscar una aerolinea en especifico", notes = "Se retorna la aerolinea asociada al ID suministrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la busqueda correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping(value = "/buscarAerolineaPorId/{id}")
    public ResponseEntity<AerolineaDto> getAerolinea(@PathVariable final Long id){
        Aerolinea aerolinea = aerolineaService.getAerolinea(id);
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Eliminar una aerolinea", notes = "Se elimina el objeto de AerolineaDto asociado al ID suministrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la eliminacion de forma correcta"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @DeleteMapping(value = "/eliminarAerolineaPorId/{id}")
    public ResponseEntity<AerolineaDto> deleteAerolinea(@PathVariable final Long id){
        Aerolinea aerolinea = aerolineaService.deleteAerolinea(id);
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Editar una aerolinea", notes = "Se recibe en el body la informacion actualizada asociada " +
            "al ID suministrado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la actualizacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PutMapping(value = "/editarAerolinea/{id}")
    public ResponseEntity<AerolineaDto> editAerolinea(@PathVariable final Long id,
                                                      @RequestBody final AerolineaDto aerolineaDto){
        Aerolinea aerolinea = aerolineaService.editAerolinea(id, Aerolinea.from(aerolineaDto));
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Asociar un vuelo con una aerolinea", notes = "Se reciben los ID del vuelo respectivo y la aerolinea " +
            "respectiva que se desean asociar.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la asignacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping(value = "/asociarAerolineaVuelo/{idAerolinea}/vuelos/{idVuelo}/add")
    public ResponseEntity<AerolineaDto> addAerolineaToVuelo(@PathVariable final Long idAerolinea,
                                                             @PathVariable final Long idVuelo){
        Aerolinea aerolinea = aerolineaService.addVueloToAerolinea(idAerolinea, idVuelo);
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Desasociar un vuelo de una aerolinea", notes = "Se reciben los ID del vuelo respectivo y la aerolinea " +
            "respectiva que se desean desasociar.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la desasociacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping(value = "/removerAsignacion/{idAerolinea}/vuelos/{idVuelo}/remove")
    public ResponseEntity<AerolineaDto> removeAerolineaFromVuelo(@PathVariable final Long idAerolinea,
                                                                  @PathVariable final Long idVuelo){
        Aerolinea aerolinea = aerolineaService.removeVueloFromAerolinea(idAerolinea, idVuelo);
        return new ResponseEntity<>(AerolineaDto.from(aerolinea), HttpStatus.OK);
    }
}
