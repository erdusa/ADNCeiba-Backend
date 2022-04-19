package com.ceiba.comun;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class DateUtils {
    private DateUtils() {
    }

    public static int obtenerCantidadDiaSemana(DayOfWeek dia, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        int cantidadDias = 0;
        while (fechaInicial.isBefore(fechaFinal)) {
            if (fechaInicial.getDayOfWeek().equals(dia)) {
                cantidadDias++;
            }
            fechaInicial = fechaInicial.plusDays(1);
        }
        return cantidadDias;
    }

}
