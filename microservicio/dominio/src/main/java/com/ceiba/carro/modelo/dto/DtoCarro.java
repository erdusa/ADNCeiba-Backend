package com.ceiba.carro.modelo.dto;

import com.ceiba.carro.enums.EnumGama;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoCarro {
    private Long id;
    private String marca;
    private Integer modelo;
    private String placa;
    private EnumGama gama;
}
