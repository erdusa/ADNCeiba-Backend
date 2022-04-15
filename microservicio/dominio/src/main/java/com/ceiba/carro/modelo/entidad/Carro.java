package com.ceiba.carro.modelo.entidad;

import com.ceiba.reserva.enums.EnumGama;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Carro {
    private Long id;
    private String marca;
    private Integer modelo;
    private String placa;
    private EnumGama gama;
}
