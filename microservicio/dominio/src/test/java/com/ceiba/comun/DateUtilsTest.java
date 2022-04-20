package com.ceiba.comun;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void deberiaDevolver5SabadosIniciandoEnSabadoYTerminandoEnSabado() {
        // arrange
        LocalDate fechaInicial = LocalDate.of(2022,4,2);
        LocalDate fechaFinal = LocalDate.of(2022,4,30);
        // act
        int valorRetornado = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.SATURDAY, fechaInicial, fechaFinal);
        // assert
        assertEquals(5, valorRetornado);
    }

    @Test
    void deberiaDevolver1DiaDeCadaDiaEnUnaSemana() {
        // arrange
        LocalDate fechaInicial = LocalDate.of(2022,4,4);
        LocalDate fechaFinal = LocalDate.of(2022,4,10);
        // act
        int cantidadLunes = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.MONDAY, fechaInicial, fechaFinal);
        int cantidadMartes = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.TUESDAY, fechaInicial, fechaFinal);
        int cantidadMiercoles = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.WEDNESDAY, fechaInicial, fechaFinal);
        int cantidadJueves = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.THURSDAY, fechaInicial, fechaFinal);
        int cantidadViernes = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.FRIDAY, fechaInicial, fechaFinal);
        int cantidadSabados = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.SATURDAY, fechaInicial, fechaFinal);
        int cantidadDomingos = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.SUNDAY, fechaInicial, fechaFinal);
        // assert
        assertEquals(1, cantidadLunes);
        assertEquals(1, cantidadMartes);
        assertEquals(1, cantidadMiercoles);
        assertEquals(1, cantidadJueves);
        assertEquals(1, cantidadViernes);
        assertEquals(1, cantidadSabados);
        assertEquals(1, cantidadDomingos);
    }

    @Test
    void deberiaDevolverLosDiasCorrectosEnUnMes() {
        // arrange
        LocalDate fechaInicial = LocalDate.of(2022,4,1);
        LocalDate fechaFinal = LocalDate.of(2022,4,30);
        // act
        int cantidadLunes = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.MONDAY, fechaInicial, fechaFinal);
        int cantidadMartes = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.TUESDAY, fechaInicial, fechaFinal);
        int cantidadMiercoles = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.WEDNESDAY, fechaInicial, fechaFinal);
        int cantidadJueves = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.THURSDAY, fechaInicial, fechaFinal);
        int cantidadViernes = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.FRIDAY, fechaInicial, fechaFinal);
        int cantidadSabados = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.SATURDAY, fechaInicial, fechaFinal);
        int cantidadDomingos = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.SUNDAY, fechaInicial, fechaFinal);
        // assert
        assertEquals(4, cantidadLunes);
        assertEquals(4, cantidadMartes);
        assertEquals(4, cantidadMiercoles);
        assertEquals(4, cantidadJueves);
        assertEquals(5, cantidadViernes);
        assertEquals(5, cantidadSabados);
        assertEquals(4, cantidadDomingos);
    }

    @Test
    void deberiaDevolverUnoSoloParaunDiaCuandoFechaInicialIgualAFechaFinal() {
        // arrange
        LocalDate fechaInicial = LocalDate.of(2022,4,1);
        LocalDate fechaFinal = LocalDate.of(2022,4,1);
        // act
        int cantidadLunes = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.MONDAY, fechaInicial, fechaFinal);
        int cantidadMartes = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.TUESDAY, fechaInicial, fechaFinal);
        int cantidadMiercoles = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.WEDNESDAY, fechaInicial, fechaFinal);
        int cantidadJueves = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.THURSDAY, fechaInicial, fechaFinal);
        int cantidadViernes = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.FRIDAY, fechaInicial, fechaFinal);
        int cantidadSabados = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.SATURDAY, fechaInicial, fechaFinal);
        int cantidadDomingos = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.SUNDAY, fechaInicial, fechaFinal);
        // assert
        assertEquals(0, cantidadLunes);
        assertEquals(0, cantidadMartes);
        assertEquals(0, cantidadMiercoles);
        assertEquals(0, cantidadJueves);
        assertEquals(1, cantidadViernes);
        assertEquals(0, cantidadSabados);
        assertEquals(0, cantidadDomingos);
    }

}