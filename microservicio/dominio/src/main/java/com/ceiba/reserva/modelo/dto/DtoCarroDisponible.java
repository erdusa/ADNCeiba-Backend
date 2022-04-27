package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoCarroDisponible {
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;
    private Long idCarro;
    private String marca;
    private Integer modelo;
    private String placa;
    private Double valor;
}
