package com.ceiba.reserva.consulta;

import com.ceiba.reserva.modelo.dto.DtoReservaVigente;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarPorCliente {

    private final DaoReserva daoReserva;

    public ManejadorListarPorCliente(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }

    public List<DtoReservaVigente> ejecutar(Long idCliente) {
        return this.daoReserva.listarVigentesPorCliente(idCliente);
    }
}
