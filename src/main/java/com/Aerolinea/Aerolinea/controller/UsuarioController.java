package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Usuario;
import com.Aerolinea.Aerolinea.model.dto.MedioPagoDto;
import com.Aerolinea.Aerolinea.model.dto.TipoVueloDto;
import com.Aerolinea.Aerolinea.model.dto.UsuarioDto;
import com.Aerolinea.Aerolinea.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios crear un nuevo usuario y registrarlo en la base de datos. " +
            "Los datos que el usuario debe diligenciar en este endpoint son: documento, nombre, apellido, celular, email. Los demas datos se " +
            "agregan automaticamente con las respectivas asignaciones.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: Secompleto la solicitud con exito, por tanto, " +
                    "se creo un nuevo usuario.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: Significa que la solicitud esta mal formulada o " +
                    "es incorrecta. Quizas algun dato erroneo en el JSON",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity: La solicitud es válida, pero no se " +
                    "puede procesar debido a un conflicto en los datos enviados.",
                    content = @Content) })

    @PostMapping("/crear")
    public ResponseEntity<UsuarioDto> addUsuario(@RequestBody final UsuarioDto usuarioDto){
        Usuario usuario = usuarioService.addUsuario(Usuario.from(usuarioDto));
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, permite a los usuarios consultar todos los usuarios que se encuentran " +
            "registrados en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito y se devuelve la información solicitada, " +
                    "respecto a la lista de usuarios.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar la lista de usuarios",
                    content = @Content) })

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDto>> getUsuarios(){
        List<Usuario> usuarios = usuarioService.getUsuarios();
        List<UsuarioDto> usuariosDto = usuarios.stream().map(UsuarioDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(usuariosDto, HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, permite buscar un usuario con base en su id unico. Los usuarios deben de suministrar el id del usuario que " +
            "desean buscar en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito y se devuelve la información solicitada, " +
                    "respecto al usuarios consultado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el usuario de la base de datos.",
                    content = @Content) })

    @GetMapping(value = "{id}")
    public ResponseEntity<UsuarioDto> getusuario(@PathVariable final Long id){
        Usuario usuario = usuarioService.getUsuario(id);
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite eliminar un usuario de la base de datos. El usuario debe de suministrar el id del usuario " +
            "que desea eliminar de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se elimino el " +
                    "usuario con el id especificado de la base de datos.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el usuario que se desea eliminar de la base de datos.",
                    content = @Content) })

    @DeleteMapping(value = "{id}")
    public ResponseEntity<UsuarioDto> deleteUsuario(@PathVariable final Long id){
        Usuario usuario = usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite editar un usuario especifico de la base de datos. Se debe de suministrar el id del usuario que se desea " +
            "editar y en el JSON se debe diligenciar los siguientes datos: documento, nombre, apellido, celular, email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se edito de forma satisfactoria " +
                    "el usuario con el id especificado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada. Podria ser un dato mal diligenciado.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el usuario que se desea editar de la base de datos.",
                    content = @Content)})

    @PutMapping(value = "{id}")
    public ResponseEntity<UsuarioDto> editUsuario(@PathVariable final Long id,
                                                    @RequestBody final UsuarioDto usuarioDto){
        Usuario usuario = usuarioService.editUsuario(id, Usuario.from(usuarioDto));
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite asignar un usuario a un boleto. Es decir, este es el metodo para reservar un vuelo. El usuario debe " +
            "suministrar el id del usuario y el id del boleto respectivos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se reservo el vuelo deseado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada. En este caso se valida " +
                    "la hora de la reserva, comparando la hora de despegue con la hora del sistema, si no cumple " +
                    "la regla de las tres horas, se lanza una excepcion y se genera este codigo de error.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el usuario o el boleto por su id de la base de datos.",
                    content = @Content)})

    @PostMapping(value = "{idUsuario}/boletos/{idBoleto}/reservar")
    public ResponseEntity<UsuarioDto> addUsuarioToBoleto(@PathVariable final Long idUsuario,
                                                           @PathVariable final Long idBoleto){
        Usuario usuario = usuarioService.addBoletoToUsuario(idUsuario, idBoleto);
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

    @Operation(summary = "Este Endpoint, le permite  remover una asociacion entre un usuario y un vuelo. Es decir, este metodo le permite " +
            "cancelar una reserva. El usuario debe suministrar el id del usuario y el id del boleto respectivos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: La solicitud se completo con exito. Se elimino reserva.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request: La solicitud es incorrecta o mal formada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found: El recurso no ha sido encontrado en el servidor. Es decir, que no fue " +
                    "posible recuperar el usuario o el boleto por su id de la base de datos.",
                    content = @Content)})

    @PostMapping(value = "{idUsuario}/boletos/{idBoleto}/remove")
    public ResponseEntity<UsuarioDto> removeBoletoFromUsuario(@PathVariable final Long idUsuario,
                                                                @PathVariable final Long idBoleto){
        Usuario usuario = usuarioService.removeBoletoFromUsuario(idUsuario, idBoleto);
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

}
