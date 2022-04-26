package com.ceiba.reserva.puerto.dao;

import com.ceiba.carro.enums.EnumGama;
import com.ceiba.reserva.modelo.dto.DtoCarroDisponible;
import com.ceiba.reserva.modelo.dto.DtoReservaVigente;

import java.time.LocalDate;
import java.util.List;

public interface DaoReserva {

    /**
     * Permite listar las reservas para el cliente
     *
     * @param idCliente identificador del cliente
     * @return las reservas para el cliente
     */
    List<DtoReservaVigente> listarVigentesPorCliente(Long idCliente);

    /**
     * Permite listar los carros disponibles para la gama y fechas seleccionadas
     *
     * @param fechaInicial fecha inicial para la consulta
     * @param fechaFinal   fecha final para la consulta
     * @param gama         gama del carro solicitado
     * @return la lista de carros disponibles para los criterios ingresados
     */
    List<DtoCarroDisponible> listarCarrosDisponibles(LocalDate fechaInicial, LocalDate fechaFinal, EnumGama gama);
}
