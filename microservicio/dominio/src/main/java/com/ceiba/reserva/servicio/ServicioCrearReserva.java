package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

public class ServicioCrearReserva {

    public static final String EL_CARRO_YA_ESTA_RESERVADO_PARA_LAS_FECHAS_SELECCIONADAS = "El carro ya está reservado para las fechas seleccionadas";
    private final RepositorioReserva repositorioReserva;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public Long ejecutar(Reserva reserva) {
        validarCarroReservado(reserva);
        return repositorioReserva.crear(reserva);
    }

    private void validarCarroReservado(Reserva reserva) {
        boolean carroYaReservado = repositorioReserva.estaCarroReservado(reserva.getCarro().getId(), reserva.getFechaInicial().toLocalDate(), reserva.getFechaFinal().toLocalDate());
        if (carroYaReservado) {
            throw new ExcepcionDuplicidad(EL_CARRO_YA_ESTA_RESERVADO_PARA_LAS_FECHAS_SELECCIONADAS);
        }
    }
}
