package com.Aerolinea.Aerolinea.model;

import com.Aerolinea.Aerolinea.model.dto.BoletoDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "boleto")
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int lugar;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medio_pago")
    private MedioPago medioPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vuelo")
    private Vuelo vuelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Boleto(){

    }

    public static Boleto from(BoletoDto boletoDto){
        Boleto boleto = new Boleto();
        boleto.setLugar(boletoDto.getLugar());
        return boleto;
    }


}
