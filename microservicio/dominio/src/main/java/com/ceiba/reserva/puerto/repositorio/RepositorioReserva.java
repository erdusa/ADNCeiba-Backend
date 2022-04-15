package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDate;

public interface RepositorioReserva {

    /**
     * Permite crear la reserva
     *
     * @param reserva datos de la reserva
     * @return identificador de la reserva creada
     */
    Long crear(Reserva reserva);

    /**
     * Permite cancelar una reserva
     *
     * @param idReserva identificador de la reserva a cancelar
     */
    void cancelar(Long idReserva);

    /**
     * Permite consultar la reserva por el identificador
     *
     * @param idReserva identificador de la reserva
     * @return la reserva consultada
     */
    Reserva consultar(Long idReserva);

    /**
     * Permite verificar si un carro ya está reservado para las fechas dadas
     *
     * @param idCarro      identificador del carro
     * @param fechaInicial fecha inicial solicitada para la reserva
     * @param fechaFinal   fecha final solicitada para la reserva
     * @return true si ya está reservado
     */
    boolean estaCarroReservado(Long idCarro, LocalDate fechaInicial, LocalDate fechaFinal);
}
