package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.comando.ComandoReserva;

import java.time.LocalDateTime;

public class ComandoReservaTestDataBuilder {

    private Long idCliente;
    private Long idCarro;
    private LocalDateTime fechaInicial;
    private Integer dias;

    public ComandoReservaTestDataBuilder() {
        this.idCliente = 1L;
        this.idCarro = 1L;
        this.fechaInicial = LocalDateTime.now();
        this.dias = 1;
    }

    public ComandoReservaTestDataBuilder conIdCarro(Long idCarro) {
        this.idCarro = idCarro;
        return this;
    }

    public ComandoReservaTestDataBuilder conFechaInicial(LocalDateTime fechaInicial) {
        this.fechaInicial = fechaInicial;
        return this;
    }

    public ComandoReservaTestDataBuilder conDias(Integer dias) {
        this.dias = dias;
        return this;
    }

    public ComandoReserva build() {
        return new ComandoReserva(
                this.idCliente,
                this.idCarro,
                this.fechaInicial,
                this.dias
        );
    }
}
