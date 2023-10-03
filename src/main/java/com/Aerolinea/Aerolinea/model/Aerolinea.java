package com.Aerolinea.Aerolinea.model;

import com.Aerolinea.Aerolinea.model.dto.AerolineaDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "aerolinea")
public class Aerolinea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Vuelo> vuelos = new ArrayList<>();

    public void addVuelo(Vuelo vuelo){
        vuelos.add(vuelo);
    }

    public void removeVuelo(Vuelo vuelo){
        vuelos.remove(vuelo);
    }
    public static Aerolinea from(AerolineaDto aerolineaDto) {
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setId(aerolineaDto.getId());
        aerolinea.setNombre(aerolineaDto.getNombre());
        return aerolinea;
    }
}
