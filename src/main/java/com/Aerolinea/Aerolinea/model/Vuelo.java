package com.Aerolinea.Aerolinea.model;

import com.Aerolinea.Aerolinea.model.dto.VueloDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "vuelo")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int lugaresDisponibles;
    private double costo;
    private String codigo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Boleto> boletos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Escala> escalas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aerolinea")
    private Aerolinea aerolinea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_vuelo")
    private TipoVuelo tipoVuelo;

    public void addBoleto(Boleto boleto){
        boletos.add(boleto);
    }

    public void removeBoleto(Boleto boleto){
        boletos.remove(boleto);
    }

    public void addEscala(Escala escala){
        escalas.add(escala);
    }

    public void removeEscala(Escala escala){
        escalas.remove(escala);
    }

    public static Vuelo from(VueloDto vueloDto){
        Vuelo vuelo = new Vuelo();
        vuelo.setLugaresDisponibles(vueloDto.getLugaresDisponibles());
        vuelo.setCosto(vueloDto.getCosto());
        vuelo.setCodigo(vueloDto.getCodigo());
        return vuelo;
    }
}
