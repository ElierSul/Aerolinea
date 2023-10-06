package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.constants.BoletoConstantes;
import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.Usuario;
import com.Aerolinea.Aerolinea.model.exception.BoletoIsAlreadyAssignedUsuarioException;
import com.Aerolinea.Aerolinea.model.exception.UsuarioNotFoundException;
import com.Aerolinea.Aerolinea.repository.UsuarioRepository;
import com.Aerolinea.Aerolinea.security.CustomerDetailService;
import com.Aerolinea.Aerolinea.security.jwt.JwtUtil;
import com.Aerolinea.Aerolinea.util.BoletoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UsuarioService implements IUsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final BoletoService boletoService;
    private final AuthenticationManager authenticationManager;
    private final CustomerDetailService customerDetailService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, BoletoService boletoService, AuthenticationManager authenticationManager, CustomerDetailService customerDetailService, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.boletoService = boletoService;
        this.authenticationManager = authenticationManager;
        this.customerDetailService = customerDetailService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Registro de un usuario {}", requestMap);
        try{
            if (validateSignUpMap(requestMap)) {
                Usuario usuario = usuarioRepository.findByEmail(requestMap.get("email"));
                if(Objects.isNull(usuario)){
                    usuarioRepository.save(getUsuarioFromMap(requestMap));
                    return BoletoUtils.getResponseEntity("Usuario creado con exito", HttpStatus.CREATED);
                } else{
                    return BoletoUtils.getResponseEntity("El usuario ya esta registrado", HttpStatus.BAD_REQUEST);
                }
            } else {
                return BoletoUtils.getResponseEntity(BoletoConstantes.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return BoletoUtils.getResponseEntity(BoletoConstantes.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Dentro del login");

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );

            if(authentication.isAuthenticated()){
                if(customerDetailService.getUserDetail().getStatus().equalsIgnoreCase("true")){
                    return new ResponseEntity<String>("{\"token\":\""
                            + jwtUtil.generateToken(customerDetailService.getUserDetail().getEmail(),
                            customerDetailService.getUserDetail().getRole())+"\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("{\"mensaje\":\"" + " Espera la aprobacion del administrador "+"\"}", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception exception){
            log.error("{}", exception);
        }
        return new ResponseEntity<String>("{\"mensaje\":\"" + " Credenciales incorrectas "+"\"}", HttpStatus.BAD_REQUEST);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap){
        if (requestMap.containsKey("documento") && requestMap.containsKey("nombre")
        && requestMap.containsKey("apellido") && requestMap.containsKey("celular") && requestMap.containsKey("email")
        && requestMap.containsKey("password") && requestMap.containsKey("equipaje")) {
            return true;
        }
        return false;
    }

    private Usuario getUsuarioFromMap(Map<String, String> requestMap){
        Usuario usuario = new Usuario();
        usuario.setDocumento(requestMap.get("documento"));
        usuario.setNombre(requestMap.get("nombre"));
        usuario.setApellido(requestMap.get("apellido"));
        usuario.setCelular(requestMap.get("celular"));
        usuario.setEmail(requestMap.get("email"));
        usuario.setPassword(requestMap.get("password"));
        usuario.setStatus("false");
        usuario.setRole("user");
        usuario.setEquipaje(Boolean.parseBoolean(requestMap.get("equipaje")));
        return usuario;
    }
    public List<Usuario> getUsuarios(){
        return StreamSupport.
                stream(usuarioRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Usuario getUsuario(Long id){
        return usuarioRepository.findById(id).orElseThrow(() ->
                new UsuarioNotFoundException(id));
    }

    public Usuario deleteUsuario(Long id){
        Usuario usuario = getUsuario(id);
        usuarioRepository.delete(usuario);
        return usuario;
    }

    @Transactional
    public Usuario editUsuario(Long id, Usuario usuario){
        Usuario usuarioToEdit = getUsuario(id);
        usuarioToEdit.setNombre(usuario.getNombre());
        usuarioToEdit.setApellido(usuario.getApellido());
        usuarioToEdit.setCelular(usuario.getCelular());
        usuarioToEdit.setEmail(usuario.getEmail());
        usuarioToEdit.setPassword(usuario.getPassword());
        usuarioToEdit.setEquipaje(usuario.isEquipaje());
        return usuarioToEdit;
    }

    @Transactional
    public Usuario addBoletoToUsuario(Long idUsuario, Long idBoleto){
        Usuario usuario = getUsuario(idUsuario);
        Boleto boleto = boletoService.getBoleto(idBoleto);
        if(Objects.nonNull(boleto.getUsuario())){
            throw new BoletoIsAlreadyAssignedUsuarioException(idBoleto, boleto.getUsuario().getId());
        }
        usuario.addBoleto(boleto);
        boleto.setUsuario(usuario);
        return usuario;
    }

    @Transactional
    public Usuario removeBoletoFromUsuario(Long idUsuario, Long idBoleto){
        Usuario usuario = getUsuario(idUsuario);
        Boleto boleto = boletoService.getBoleto(idBoleto);
        usuario.removeBoleto(boleto);
        return usuario;
    }


}
