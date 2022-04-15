package com.ceiba.reserva.modelo.dto;

import com.ceiba.carro.modelo.dto.DtoCarro;
import com.ceiba.cliente.modelo.dto.DtoCliente;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DtoReserva {

    private Long id;
    private DtoCliente dtoCliente;
    private DtoCarro dtoCarro;
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;
    private Double valor;
}
