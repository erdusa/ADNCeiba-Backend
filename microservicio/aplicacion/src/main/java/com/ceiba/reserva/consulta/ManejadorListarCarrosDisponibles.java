package com.ceiba.reserva.consulta;

import com.ceiba.carro.enums.EnumGama;
import com.ceiba.carro.modelo.dto.DtoCarro;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ManejadorListarCarrosDisponibles {

    private final DaoReserva daoReserva;

    public ManejadorListarCarrosDisponibles(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }

    public List<DtoCarro> ejecutar(LocalDate fechaInicial, LocalDate fechaFinal, EnumGama gama) {
        return this.daoReserva.listarCarrosDisponibles(fechaInicial, fechaFinal, gama);
    }
}
