package com.ceiba.reserva.modelo.dto;

import com.ceiba.carro.enums.EnumGama;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoReservaVigente {

    private Long idReserva;
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;
    private String marca;
    private Integer modelo;
    private String placa;
    private EnumGama gama;
    private Double valor;
}
