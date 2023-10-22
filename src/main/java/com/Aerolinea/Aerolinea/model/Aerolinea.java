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

    private String nombre;
    private String prefijo;
    private int consecutivo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Vuelo> vuelos = new ArrayList<>();

    public String generarCodigoVuelo() {
        return prefijo + String.format("%03d", consecutivo + 1);
    }

    public void incrementarConsecutivo() {
        consecutivo++;
    }

    public void addVuelo(Vuelo vuelo){
        vuelos.add(vuelo);
    }

    public void removeVuelo(Vuelo vuelo){
        vuelos.remove(vuelo);
    }
    public static Aerolinea from(AerolineaDto aerolineaDto) {
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setNombre(aerolineaDto.getNombre());
        aerolinea.setPrefijo(aerolineaDto.getPrefijo());
        aerolinea.setConsecutivo(aerolineaDto.getConsecutivo());
        return aerolinea;
    }
}
