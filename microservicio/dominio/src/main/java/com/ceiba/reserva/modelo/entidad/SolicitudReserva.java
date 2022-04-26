package com.ceiba.reserva.modelo.entidad;

import com.ceiba.carro.modelo.entidad.Carro;
import com.ceiba.cliente.modelo.entidad.Cliente;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SolicitudReserva {

    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;
    private Cliente cliente;
    private Carro carro;

    public SolicitudReserva(LocalDateTime fechaInicial, LocalDateTime fechaFinal, Cliente cliente, Carro carro) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.cliente = cliente;
        this.carro = carro;
    }
}
