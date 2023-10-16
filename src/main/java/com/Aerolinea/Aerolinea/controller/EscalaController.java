package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Escala;
import com.Aerolinea.Aerolinea.model.dto.EscalaDto;
import com.Aerolinea.Aerolinea.service.EscalaService;
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
@RequestMapping("api/v1/escalas")
@Api(tags = "Escalas", description = "Controlador de escalas")
public class EscalaController {

    private final EscalaService escalaService;

    @Autowired
    public EscalaController(EscalaService escalaService) {
        this.escalaService = escalaService;
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Registrar una escala", notes = "Se recibe por el body un objeto de EscalasDto" +
            "y esta se registra en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo el registro correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping("/crearEscala")
    public ResponseEntity<EscalaDto> addEscala(@RequestBody final EscalaDto escalaDto){
        Escala escala = escalaService.addEscala(Escala.from(escalaDto));
        return new ResponseEntity<>(EscalaDto.from(escala), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Mostrar escalas", notes = "Se retorna una lista de escalas de EscalasDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se muestra la informacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping("/listarEscalas")
    public ResponseEntity<List<EscalaDto>> getEscalas(){
        List<Escala> escalas = escalaService.getEscalas();
        List<EscalaDto> escalaDtos = escalas.stream().map(EscalaDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(escalaDtos, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Buscar una escala en especifico", notes = "Se retorna la escala asociada al ID suministrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la busqueda correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping(value = "/buscarEscalaPorId{id}")
    public ResponseEntity<EscalaDto> getEscala(@PathVariable final Long id){
        Escala escala = escalaService.getEscala(id);
        return new ResponseEntity<>(EscalaDto.from(escala), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Eliminar una escala", notes = "Se elimina el objeto de EscalaDto asociado al ID suministrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la eliminacion de forma correcta"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @DeleteMapping(value = "/eliminarEscalaPorId/{id}")
    public ResponseEntity<EscalaDto> deleteEscala(@PathVariable final Long id){
        Escala escala = escalaService.deleteEscala(id);
        return new ResponseEntity<>(EscalaDto.from(escala), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Editar una escala", notes = "Se recibe en el body la informacion actualizada asociada " +
            "al ID suministrado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la actualizacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PutMapping(value = "/editarEscala/{id}")
    public ResponseEntity<EscalaDto> editEscala(@PathVariable final Long id,
                                                  @RequestBody final EscalaDto escalaDto){
        Escala escala = escalaService.editEscala(id, Escala.from(escalaDto));
        return new ResponseEntity<>(EscalaDto.from(escala), HttpStatus.OK);
    }

}
