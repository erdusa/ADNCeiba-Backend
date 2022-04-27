package com.ceiba.reserva.consulta;

import com.ceiba.carro.enums.EnumGama;
import com.ceiba.reserva.modelo.dto.DtoCarroDisponible;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ManejadorListarCarrosDisponibles {

    private final DaoReserva daoReserva;

    public ManejadorListarCarrosDisponibles(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }

    public List<DtoCarroDisponible> ejecutar(LocalDateTime fechaInicial, Integer dias, EnumGama gama) {
        LocalDateTime fechaFinal = fechaInicial.plusDays(dias);
        return this.daoReserva.listarCarrosDisponibles(fechaInicial, fechaFinal, gama);
    }
}
