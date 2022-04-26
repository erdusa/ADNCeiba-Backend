package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.carro.modelo.entidad.Carro;
import com.ceiba.carro.modelo.servicio.testdatabuilder.CarroTestDataBuilder;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.modelo.servicio.testdatabuilder.ClienteTestDataBuilder;
import com.ceiba.reserva.modelo.entidad.SolicitudReserva;

import java.time.LocalDateTime;

public class SolicitudReservaTestDataBuilder {

    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;
    private Cliente cliente;
    private Carro carro;

    public SolicitudReservaTestDataBuilder() {
        this.cliente = new ClienteTestDataBuilder().build();
        this.carro = new CarroTestDataBuilder().build();
        this.fechaInicial = LocalDateTime.now();
        this.fechaFinal = fechaInicial.plusDays(1);
    }

    public SolicitudReservaTestDataBuilder conCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public SolicitudReservaTestDataBuilder conCarro(Carro carro) {
        this.carro = carro;
        return this;
    }

    public SolicitudReservaTestDataBuilder conFechaInicial(LocalDateTime fechaInicial) {
        this.fechaInicial = fechaInicial;
        return this;
    }

    public SolicitudReservaTestDataBuilder conFechaFinal(LocalDateTime fechaFinal) {
        this.fechaFinal = fechaFinal;
        return this;
    }

    public SolicitudReserva build() {
        return new SolicitudReserva(fechaInicial, fechaFinal, cliente, carro);
    }
}
