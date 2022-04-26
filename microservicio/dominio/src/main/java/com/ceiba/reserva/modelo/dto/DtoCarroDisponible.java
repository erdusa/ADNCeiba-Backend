package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoCarroDisponible {
    private Long idCarro;
    private String marca;
    private Integer modelo;
    private String placa;
    private Double valor;
}
