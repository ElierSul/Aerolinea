package com.Aerolinea.Aerolinea.model;

import com.Aerolinea.Aerolinea.model.dto.MedioPagoDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "medio_pago")
public class MedioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Boleto> boletos = new ArrayList<>();

    public void addBoleto(Boleto boleto){
        boletos.add(boleto);
    }

    public void removeBoleto(Boleto boleto){
        boletos.remove(boleto);
    }

    public static MedioPago from(MedioPagoDto medioPagoDto){
        MedioPago medioPago = new MedioPago();
        medioPago.setNombre(medioPagoDto.getNombre());
        return medioPago;
    }
}
