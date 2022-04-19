package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.comando.ComandoReserva;

import java.time.LocalDateTime;

public class ComandoReservaTestDataBuilder {

    private Long id;
    private Long idCliente;
    private Long idCarro;
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;

    public ComandoReservaTestDataBuilder() {
        this.id = 1L;
        this.idCliente = 1L;
        this.idCarro = 1L;
        this.fechaInicial = LocalDateTime.now();
        this.fechaFinal = fechaInicial.plusDays(1);
    }

    public ComandoReserva build() {
        return new ComandoReserva(
                this.id,
                this.idCliente,
                this.idCarro,
                this.fechaInicial,
                this.fechaFinal
        );
    }
}
