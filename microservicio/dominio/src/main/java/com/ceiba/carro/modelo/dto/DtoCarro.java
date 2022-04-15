package com.ceiba.carro.modelo.dto;

import com.ceiba.reserva.enums.EnumGama;
import lombok.Getter;

@Getter
public class DtoCarro {
    private Long id;
    private String marca;
    private Integer modelo;
    private String placa;
    private EnumGama gama;
}
