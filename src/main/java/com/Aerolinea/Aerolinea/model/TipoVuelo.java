package com.Aerolinea.Aerolinea.model;

import com.Aerolinea.Aerolinea.model.dto.TipoVueloDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tipo_vuelo")
public class TipoVuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean privado;
    private boolean publico;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Vuelo> vuelos = new ArrayList<>();

    public void addVuelo(Vuelo vuelo){
        vuelos.add(vuelo);
    }

    public void removeVuelo(Vuelo vuelo){
        vuelos.remove(vuelo);
    }

    public static TipoVuelo from(TipoVueloDto tipoVueloDto){
        TipoVuelo tipoVuelo = new TipoVuelo();
        tipoVuelo.setPublico(tipoVueloDto.isPublico());
        tipoVuelo.setPrivado(tipoVueloDto.isPrivado());
        return tipoVuelo;
    }
}
