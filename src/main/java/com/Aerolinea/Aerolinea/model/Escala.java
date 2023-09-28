package com.Aerolinea.Aerolinea.model;

import com.Aerolinea.Aerolinea.model.dto.EscalaDto;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Escala")
public class Escala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numTrayectos;

    private String serialNumber;

    @ManyToOne
    private Traslado traslado;

    public Escala() {
    }

    public static Escala from(EscalaDto escalaDto){
        Escala escala = new Escala();
        escala.setSerialNumber(escalaDto.getSerialNumber());
        return escala;
    }


}
