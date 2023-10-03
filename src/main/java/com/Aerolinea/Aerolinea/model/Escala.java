package com.Aerolinea.Aerolinea.model;

import com.Aerolinea.Aerolinea.model.dto.EscalaDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
@Table(name = "traslado")
public class Escala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paisOrigen;
    private String paisDestino;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String aeropuertoOrigen;
    private String aeropuertoDestino;

    @Temporal(TemporalType.DATE)
    private Date fechaOrigen;

    @Temporal(TemporalType.DATE)
    private Date fechaDestino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vuelo")
    private Vuelo vuelo;

    public Escala(){}

    public static Escala from(EscalaDto escalaDto){
        Escala escala = new Escala();
        escala.setPaisOrigen(escalaDto.getPaisOrigen());
        escala.setPaisDestino(escalaDto.getPaisDestino());
        escala.setCiudadOrigen(escalaDto.getCiudadOrigen());
        escala.setCiudadDestino(escalaDto.getCiudadDestino());
        escala.setAeropuertoOrigen(escalaDto.getAeropuertoOrigen());
        escala.setAeropuertoDestino(escalaDto.getAeropuertoDestino());
        escala.setFechaOrigen(escalaDto.getFechaOrigen());
        escala.setFechaDestino(escalaDto.getFechaDestino());
        return escala;
    }
}
