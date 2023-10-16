package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.dto.BoletoDto;
import com.Aerolinea.Aerolinea.service.BoletoService;
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
@RequestMapping("api/v1/boletos")
@Api(tags = "Boletos", description = "Controlador de boletos")
public class BoletoController {

    private final BoletoService boletoService;

    @Autowired
    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Registrar un boleto", notes = "Se recibe por el body un objeto de BoletoDto" +
            "y esta se registra en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo el registro correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping("/crearBoleto")
    public ResponseEntity<BoletoDto> addBoleto(@RequestBody final BoletoDto boletoDto){
        Boleto boleto = boletoService.addBoleto(Boleto.from(boletoDto));
        return new ResponseEntity<>(BoletoDto.from(boleto), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Mostrar boletos", notes = "Se retorna una lista de boletos de BoletosDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se muestra la informacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping("/listarBoletos")
    public ResponseEntity<List<BoletoDto>> getBoletos(){
        List<Boleto> boletos = boletoService.getBoletos();
        List<BoletoDto> boletosDto = boletos.stream().map(BoletoDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(boletosDto, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Buscar un boleto en especifico", notes = "Se retorna el boleto asociado al ID suministrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la busqueda correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping(value = "/buscarBoleto/{id}")
    public ResponseEntity<BoletoDto> getBoleto(@PathVariable final Long id){
        Boleto boleto = boletoService.getBoleto(id);
        return new ResponseEntity<>(BoletoDto.from(boleto), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Eliminar un boleto", notes = "Se elimina el objeto de BoletoDto asociado al ID suministrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la eliminacion de forma correcta"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @DeleteMapping(value = "/eliminarBoleto/{id}")
    public ResponseEntity<BoletoDto> deleteBoleto(@PathVariable final Long id){
        Boleto boleto = boletoService.deleteBoleto(id);
        return new ResponseEntity<>(BoletoDto.from(boleto), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Editar un boleto", notes = "Se recibe en el body la informacion actualizada asociada " +
            "al ID suministrado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la actualizacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PutMapping(value = "/editarBoleto/{id}")
    public ResponseEntity<BoletoDto> editBoleto(@PathVariable final Long id,
                                                @RequestBody final BoletoDto boletoDto){
        Boleto editedBoleto = boletoService.editBoleto(id, Boleto.from(boletoDto));
        return new ResponseEntity<>(BoletoDto.from(editedBoleto), HttpStatus.OK);
    }
}
