package com.ceiba.reserva.consulta;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarPorCliente {

    private final DaoReserva daoReserva;

    public ManejadorListarPorCliente(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }

    public List<DtoReserva> ejecutar(Long idCliente) {
        return this.daoReserva.listarPorCliente(idCliente);
    }
}
