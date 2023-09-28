package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.Usuario;
import com.Aerolinea.Aerolinea.model.exception.BoletoIsAlreadyAssignedMedioPagoException;
import com.Aerolinea.Aerolinea.model.exception.BoletoIsAlreadyAssignedUsuarioException;
import com.Aerolinea.Aerolinea.model.exception.MedioPagoNotFoundException;
import com.Aerolinea.Aerolinea.model.exception.UsuarioNotFoundException;
import com.Aerolinea.Aerolinea.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BoletoService boletoService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, BoletoService boletoService) {
        this.usuarioRepository = usuarioRepository;
        this.boletoService = boletoService;
    }

    public Usuario addUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
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
