package com.ceiba.reserva.shared;

import com.ceiba.comun.DateUtils;
import com.ceiba.comun.NumberUtils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public final class CalcularValorReserva {

    public static final int PORCENTAJE_AUMENTO_FINES_SEMANA = 10;

    private CalcularValorReserva() {
    }

    public static double ejecutar(Double valorCarro, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        long diasReserva = DAYS.between(fechaInicial, fechaFinal);
        int diasFinesSemana = calcularFinesDeSemanaSinIncluirFechaEntrega(fechaInicial, fechaFinal);
        long diasEntreSemana = diasReserva - diasFinesSemana;

        double valorEntreSemana = valorCarro * diasEntreSemana;
        double valorFinesDeSemana = NumberUtils.sumarPorcentaje(valorCarro, PORCENTAJE_AUMENTO_FINES_SEMANA) * diasFinesSemana;

        return valorEntreSemana + valorFinesDeSemana;
    }

    private static int calcularFinesDeSemanaSinIncluirFechaEntrega(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        return DateUtils.obtenerCantidadDiaSemana(
                DayOfWeek.SATURDAY,
                fechaInicial.toLocalDate(),
                fechaFinal.toLocalDate().minusDays(1)
        )
                + DateUtils.obtenerCantidadDiaSemana(
                DayOfWeek.SUNDAY,
                fechaInicial.toLocalDate(),
                fechaFinal.toLocalDate().minusDays(1)
        );
    }

}
