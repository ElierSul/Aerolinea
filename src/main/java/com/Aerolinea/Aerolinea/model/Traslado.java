package com.Aerolinea.Aerolinea.model;

import com.Aerolinea.Aerolinea.model.dto.TrasladoDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Traslado")
public class Traslado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pais")
    private String pais;

    @Column(name = "estado")
    private String estado;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "aeropuerto")
    private String aeropuerto;

    @Column(name = "fechaOrigen")
    private Date fechaOrigen;

    @Column(name = "fechaDestino")
    private Date fechaDestino;

    @Column(name = "solo_ida")
    private boolean soloIda;

    @Column(name = "ida_vuelta")
    private boolean idaVuelta;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "escala_id")
    private List<Escala> escalas = new ArrayList<>();

    public void addEscala(Escala escala){
        escalas.add(escala);
    }

    public void removeEscala(Escala escala){
        escalas.remove(escala);
    }

    public static Traslado from(TrasladoDto trasladoDto){
        Traslado traslado = new Traslado();

        traslado.setPais(trasladoDto.getPais());
        traslado.setEstado(trasladoDto.getEstado());
        traslado.setCiudad(trasladoDto.getCiudad());
        traslado.setAeropuerto(trasladoDto.getAeropuerto());
        traslado.setSoloIda(trasladoDto.isSoloIda());
        traslado.setIdaVuelta(trasladoDto.isIdaVuelta());
        return traslado;
    }
}
