package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDateTime;

public class ReservaTestDataBuilder {

    private Long id;
    private Long idCliente;
    private Long idCarro;
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;

    public ReservaTestDataBuilder() {

        this.id = 1L;
        this.idCliente = 1L;
        this.idCarro = 1L;
        this.fechaInicial = LocalDateTime.now();
        this.fechaFinal = fechaInicial.plusDays(1);
    }

    public ReservaTestDataBuilder conCliente(Long idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public ReservaTestDataBuilder conCarro(Long idCarro) {
        this.idCarro = idCarro;
        return this;
    }

    public ReservaTestDataBuilder conFechaInicial(LocalDateTime fechaInicial) {
        this.fechaInicial = fechaInicial;
        return this;
    }

    public ReservaTestDataBuilder conFechaFinal(LocalDateTime fechaFinal) {
        this.fechaFinal = fechaFinal;
        return this;
    }

    public Reserva build() {
        return new Reserva(id, idCliente, idCarro, fechaInicial, fechaFinal);
    }
}
