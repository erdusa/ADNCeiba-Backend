package com.ceiba.reserva.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.enums.EnumEstadoReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservaTest {

    @Test
    void deberiaCrearCorrectamenteLaReserva() {
        // arrange
        LocalDateTime fechaInicial = LocalDateTime.now();
        // act
        Reserva reserva = new ReservaTestDataBuilder()
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaInicial.plusDays(1))
                .build();
        // assert
        assertEquals(1L, reserva.getId());
        assertEquals(1L, reserva.getIdCliente());
        assertEquals(1L, reserva.getIdCarro());
        assertEquals(fechaInicial, reserva.getFechaInicial());
        assertEquals(fechaInicial.plusDays(1), reserva.getFechaFinal());
        assertEquals(EnumEstadoReserva.VIGENTE.toString(), reserva.getEstado());
    }

    @Test
    void deberiaFallarSinCliente() {
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conCliente(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, Reserva.DEBE_INGRESAR_UN_CLIENTE_EXISTENTE);
    }

    @Test
    void deberiaFallarSinCarro() {
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conCarro(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, Reserva.DEBE_SELECCIONAR_UN_CARRO_EXISTENTE);
    }

    @Test
    void deberiaFallarSinFechaInicial() {
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaInicial(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, Reserva.DEBE_INGRESAR_LA_FECHA_INICIAL);
    }

    @Test
    void deberiaFallarSiFechaInicialMenorALaActual() {
        // arrange
        LocalDateTime fechaInicial = LocalDateTime.now().minusDays(1);
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaInicial(fechaInicial);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, Reserva.LA_FECHA_INICIAL_DEBE_SER_MAYOR_A_LA_FECHA_ACTUAL);
    }

    @Test
    void deberiaFallarSinFechaFinal() {
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaFinal(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, Reserva.DEBE_INGRESAR_LA_FECHA_FINAL);
    }

    @Test
    void deberiaFallarSiFechaInicialMeyorALaFinal() {
        // arrange
        LocalDateTime fechaFinal = LocalDateTime.now().minusDays(1);
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaFinal(fechaFinal);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, Reserva.LA_FECHA_INICIAL_NO_PUEDE_SER_MAYOR_A_LA_FECHA_FINAL);
    }

    @Test
    void deberiaCrearCorrectamenteSiSeReservaPor7Dias() {
        // arrange
        LocalDateTime fechaInicial = LocalDateTime.now();
        LocalDateTime fechaFinal = LocalDateTime.now().plusDays(7);
        // act
        Reserva reserva = new ReservaTestDataBuilder()
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaFinal)
                .build();
        //assert
        assertEquals(1L, reserva.getId());
        assertEquals(1L, reserva.getIdCliente());
        assertEquals(1L, reserva.getIdCarro());
        assertEquals(fechaInicial, reserva.getFechaInicial());
        assertEquals(fechaFinal, reserva.getFechaFinal());
        assertEquals(EnumEstadoReserva.VIGENTE.toString(), reserva.getEstado());
    }

    @Test
    void deberiaFallarSiSeReservaPorMasDe7Dias() {
        // arrange
        LocalDateTime fechaInicial = LocalDateTime.now();
        LocalDateTime fechaFinal = LocalDateTime.now().plusDays(8);
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder()
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaFinal);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, Reserva.NO_SE_PUEDE_HACER_LA_RESERVA_POR_MAS_DE_7_DIAS);
    }

}