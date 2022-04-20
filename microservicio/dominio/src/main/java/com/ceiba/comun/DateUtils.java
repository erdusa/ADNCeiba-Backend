package com.ceiba.comun;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class DateUtils {
    private DateUtils() {
    }

    public static int obtenerCantidadDiaSemana(DayOfWeek dia, LocalDate fechaInicial, LocalDate fechaFinal) {
        int cantidadDias = 0;
        while (!fechaInicial.isAfter(fechaFinal)) {
            if (fechaInicial.getDayOfWeek().equals(dia)) {
                cantidadDias++;
            }
            fechaInicial = fechaInicial.plusDays(1);
        }
        return cantidadDias;
    }

}
