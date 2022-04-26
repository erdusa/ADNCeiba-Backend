package com.ceiba.reserva.comando.fabrica;

import com.ceiba.carro.modelo.entidad.Carro;
import com.ceiba.carro.puerto.repositorio.RepositorioCarro;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudReserva {


    private final RepositorioCarro repositorioCarro;
    private final RepositorioCliente repositorioCliente;

    public FabricaSolicitudReserva(RepositorioCarro repositorioCarro, RepositorioCliente repositorioCliente) {
        this.repositorioCarro = repositorioCarro;
        this.repositorioCliente = repositorioCliente;
    }

    public SolicitudReserva crear(ComandoReserva comandoReserva) {
        Carro carro = this.repositorioCarro.consultar(comandoReserva.getIdCarro());
        Cliente cliente = this.repositorioCliente.consultar(comandoReserva.getIdCliente());

        return new SolicitudReserva(
                comandoReserva.getFechaInicial(),
                comandoReserva.getFechaFinal(),
                cliente,
                carro
        );
    }
}
