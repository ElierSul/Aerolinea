package com.Aerolinea.Aerolinea.service;

import com.Aerolinea.Aerolinea.model.Boleto;
import com.Aerolinea.Aerolinea.model.Escala;
import com.Aerolinea.Aerolinea.model.Usuario;
import com.Aerolinea.Aerolinea.model.Vuelo;
import com.Aerolinea.Aerolinea.model.exception.BoletoIsAlreadyAssignedUsuarioException;
import com.Aerolinea.Aerolinea.model.exception.UsuarioNotFoundException;
import com.Aerolinea.Aerolinea.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class UsuarioService{

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, BoletoService boletoService) {
        this.usuarioRepository = usuarioRepository;
        this.boletoService = boletoService;
    }

    private final BoletoService boletoService;

    public Usuario addUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
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
        return usuarioToEdit;
    }

    @Transactional
    public Usuario addBoletoToUsuario(Long idUsuario, Long idBoleto){
        Usuario usuario = getUsuario(idUsuario);
        Boleto boleto = boletoService.getBoleto(idBoleto);
        if(Objects.nonNull(boleto.getUsuario())){
            throw new BoletoIsAlreadyAssignedUsuarioException(idBoleto, boleto.getUsuario().getId());
        }

        Vuelo vuelo = boleto.getVuelo();

        Escala escala = vuelo.getEscalas().get(0);
        Date fechaDespegue = escala.getFechaOrigen();

        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaDespegue);
        cal.add(Calendar.HOUR_OF_DAY, -3);
        Date fechaMinimaReserva = cal.getTime();

        Date horaActual = new Date();

        if (horaActual.after(fechaMinimaReserva)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La reserva debe realizarse al menos 3 horas antes de la hora de despegue.");
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

    public List<Usuario> getUsuarios(){
        return StreamSupport.
                stream(usuarioRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
