package com.ceiba.reserva.modelo.dto;

import com.ceiba.carro.modelo.dto.DtoCarro;
import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.reserva.enums.EnumEstadoReserva;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoReserva {

    private Long id;
    private DtoCliente dtoCliente;
    private DtoCarro dtoCarro;
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;
    private Double valor;
    private EnumEstadoReserva estado;
}
