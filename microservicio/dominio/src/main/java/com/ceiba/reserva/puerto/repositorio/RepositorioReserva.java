package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.carro.modelo.entidad.Carro;
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
     * @param reserva reserva a cancelar
     */
    void cancelar(Reserva reserva);

    /**
     * Permite consultar la reserva por el identificador
     *
     * @param idReserva identificador de la reserva
     * @return la reserva consultada
     */
    Reserva consultar(Long idReserva);

    /**
     * Obtiene los datos del carro siempre y cuando no esté reservado
     *
     * @param idCarro      identificador del carro
     * @param fechaInicial fecha inicial solicitada para la reserva
     * @param fechaFinal   fecha final solicitada para la reserva
     * @return los datos del carro
     */
    Carro obtenerCarroSiNoEstaReservado(Long idCarro, LocalDate fechaInicial, LocalDate fechaFinal);
}
