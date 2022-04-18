package com.ceiba.reserva.modelo.servicio.testdatabuilder;

import com.ceiba.carro.enums.EnumGama;
import com.ceiba.carro.modelo.entidad.Carro;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDateTime;

public class ReservaTestDataBuilder {

    private Long id;
    private Cliente cliente;
    private Carro carro;
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;

    public ReservaTestDataBuilder() {

        this.id = 1L;
        this.cliente = new Cliente(1L, "8000", "Eudis Rene Duarte");
        this.carro = new Carro(1L, "Toyota", 2022, "MZF985", EnumGama.ALTA);
        this.fechaInicial = LocalDateTime.now();
        this.fechaFinal = fechaInicial.plusDays(1);
    }

    public ReservaTestDataBuilder conCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public ReservaTestDataBuilder conCarro(Carro carro) {
        this.carro = carro;
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
        return new Reserva(id, cliente, carro, fechaInicial, fechaFinal);
    }
}
