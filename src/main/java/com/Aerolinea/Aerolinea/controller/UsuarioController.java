package com.Aerolinea.Aerolinea.controller;

import com.Aerolinea.Aerolinea.model.Usuario;
import com.Aerolinea.Aerolinea.model.dto.UsuarioDto;
import com.Aerolinea.Aerolinea.service.UsuarioService;
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

    @PostMapping
    public ResponseEntity<UsuarioDto> addUsuario(@RequestBody final UsuarioDto usuarioDto){
        Usuario usuario = usuarioService.addUsuario(Usuario.from(usuarioDto));
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getUsuarios(){
        List<Usuario> usuarios = usuarioService.getUsuarios();
        List<UsuarioDto> usuariosDto = usuarios.stream().map(UsuarioDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(usuariosDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UsuarioDto> getusuario(@PathVariable final Long id){
        Usuario usuario = usuarioService.getUsuario(id);
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<UsuarioDto> deleteUsuario(@PathVariable final Long id){
        Usuario usuario = usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<UsuarioDto> editUsuario(@PathVariable final Long id,
                                                    @RequestBody final UsuarioDto usuarioDto){
        Usuario usuario = usuarioService.editUsuario(id, Usuario.from(usuarioDto));
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

    @PostMapping(value = "{idUsuario}/boletos/{idBoleto}/add")
    public ResponseEntity<UsuarioDto> addEscalaToTraslado(@PathVariable final Long idUsuario,
                                                           @PathVariable final Long idBoleto){
        Usuario usuario = usuarioService.addBoletoToUsuario(idUsuario, idBoleto);
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }

    @PostMapping(value = "{idUsuario}/boletos/{idBoleto}/remove")
    public ResponseEntity<UsuarioDto> removeBoletoFromUsuario(@PathVariable final Long idUsuario,
                                                                @PathVariable final Long idBoleto){
        Usuario usuario = usuarioService.removeBoletoFromUsuario(idUsuario, idBoleto);
        return new ResponseEntity<>(UsuarioDto.from(usuario), HttpStatus.OK);
    }
}
