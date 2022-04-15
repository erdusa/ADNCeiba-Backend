package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public class ServicioCancelarReserva {

    public static final String SOLO_SE_PUEDE_CANCELAR_LA_RESERVA_2_DIAS_ANTES_Y_MAXIMO_HASTA_LAS_7_PM = "Sólo se puede cancelar la reserva 2 días antes y máximo hasta las 7 PM";
    private final RepositorioReserva repositorioReserva;

    public ServicioCancelarReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public void ejecutar(Long idReserva) {
        validarSiPuedeCancelar(idReserva);
        repositorioReserva.cancelar(idReserva);
    }

    private void validarSiPuedeCancelar(Long idReserva) {
        LocalDateTime fechaActual = LocalDateTime.now();
        Reserva reserva = repositorioReserva.consultar(idReserva);

        boolean esDosDiasAntesDeLas7PM =
                DAYS.between(fechaActual.toLocalDate(), reserva.getFechaInicial().toLocalDate()) >= 2
                        && fechaActual.getHour() <= 19;

        if (!esDosDiasAntesDeLas7PM) {
            throw new ExcepcionValorInvalido(SOLO_SE_PUEDE_CANCELAR_LA_RESERVA_2_DIAS_ANTES_Y_MAXIMO_HASTA_LAS_7_PM);
        }

    }
}
