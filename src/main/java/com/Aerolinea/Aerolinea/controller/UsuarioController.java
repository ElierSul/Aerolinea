package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.constants.BoletoConstantes;
import com.Aerolinea.Aerolinea.model.Usuario;
import com.Aerolinea.Aerolinea.model.dto.UsuarioDto;
import com.Aerolinea.Aerolinea.service.UsuarioService;
import com.Aerolinea.Aerolinea.util.BoletoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("usuarios")
@Api(tags = "Usuario", description = "Controlador de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Registrar un usuario", notes = "Se recibe por el body un objeto de UsuarioDto" +
            "y esta se registra en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo el registro correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping("/signup")
    public ResponseEntity<String> addUsuario(@RequestBody(required = true)Map<String, String> requestMap){
        try{
            return usuarioService.signUp(requestMap);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return BoletoUtils.getResponseEntity(BoletoConstantes.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Iniciar sesion en el sistema", notes = "Se recibe por el body las " +
            "credenciales del usuario que desea inciar sesion.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo el inicio de sesion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap){
        try{
            return usuarioService.login(requestMap);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return BoletoUtils.getResponseEntity(BoletoConstantes.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //@PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Mostrar usuarios", notes = "Se retorna una lista de usuarios de UsuarioDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se muestra la informacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<UsuarioDto>> getUsuarios(){
        List<Usuario> usuarios = usuarioService.getUsuarios();
        List<UsuarioDto> usuariosDto = usuarios.stream().map(UsuarioDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(usuariosDto, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Buscar un usuario en especifico", notes = "Se retorna el usuario asociado al ID suministrado de UsuarioDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se muestra la informacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping("/buscarUsuario/{id}")
    public ResponseEntity<UsuarioDto> getusuario(@PathVariable final Long id){
        Usuario usuario = usuarioService.getUsuario(id);
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

    @ApiOperation(value = "Eliminar un usuario", notes = "Se elimina el objeto de UsuarioDto asociado al ID suministrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la eliminacion de forma correcta"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @DeleteMapping("eliminarUsuario/{id}")
    public ResponseEntity<UsuarioDto> deleteUsuario(@PathVariable final Long id){
        Usuario usuario = usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Editar un usuario", notes = "Se recibe en el body la informacion actualizada asociada " +
            "al ID suministrado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la actualizacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PutMapping("/editarUsuario/{id}")
    public ResponseEntity<UsuarioDto> editUsuario(@PathVariable final Long id,
                                                    @RequestBody final UsuarioDto usuarioDto){
        Usuario usuario = usuarioService.editUsuario(id, Usuario.from(usuarioDto));
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Asociar un usuario a un boleto", notes = "Se reciben los ID del usuario respectivo y el boleto " +
            "respectivo que se desean asociar.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la asociacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping("/reservar/{idUsuario}/boletos/{idBoleto}/add")
    public ResponseEntity<UsuarioDto> addUsuarioToBoleto(@PathVariable final Long idUsuario,
                                                           @PathVariable final Long idBoleto){
        Usuario usuario = usuarioService.addBoletoToUsuario(idUsuario, idBoleto);
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Desasociar un usuario de un boleto", notes = "Se reciben los ID del usuario respectivo y el boleto " +
            "respectiva que se desean desasociar.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se realizo la desasociacion correctamente"),
            @ApiResponse(code = 400, message = "Bad Request, se ingreso algo mal, verifica la informacion"),
            @ApiResponse(code = 301, message = "Permisos no otorgados y/o credenciales erroneas"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping("eliminarReserva/{idUsuario}/boletos/{idBoleto}/remove")
    public ResponseEntity<UsuarioDto> removeBoletoFromUsuario(@PathVariable final Long idUsuario,
                                                                @PathVariable final Long idBoleto){
        Usuario usuario = usuarioService.removeBoletoFromUsuario(idUsuario, idBoleto);
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }
}
