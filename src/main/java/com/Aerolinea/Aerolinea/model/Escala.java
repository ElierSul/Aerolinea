package com.Aerolinea.Aerolinea.model;

import com.Aerolinea.Aerolinea.model.dto.EscalaDto;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "escala")
public class Escala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number")
    private String serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_traslado")
    private Traslado traslado;

    public Escala() {
    }

    public static Escala from(EscalaDto escalaDto){
        Escala escala = new Escala();
        escala.setSerialNumber(escalaDto.getSerialNumber());
        return escala;
    }


}
