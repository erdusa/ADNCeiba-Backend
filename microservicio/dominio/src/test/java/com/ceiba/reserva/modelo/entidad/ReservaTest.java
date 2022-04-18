package com.ceiba.reserva.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.carro.enums.EnumGama;
import com.ceiba.carro.modelo.entidad.Carro;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.modelo.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
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
        assertEquals(1L, reserva.getCliente().getId());
        assertEquals(1L, reserva.getCarro().getId());
        assertEquals(fechaInicial, reserva.getFechaInicial());
        assertEquals(fechaInicial.plusDays(1), reserva.getFechaFinal());
        assertEquals(100, reserva.getValor());

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
    void deberiaFallarSinClienteId() {
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder()
                .conCliente(new Cliente(null, null, null));
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
    void deberiaFallarSinCarroId() {
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conCarro(new Carro(null, null, null, null, null));
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
        assertEquals(1L, reserva.getCliente().getId());
        assertEquals(1L, reserva.getCarro().getId());
        assertEquals(fechaInicial, reserva.getFechaInicial());
        assertEquals(fechaFinal, reserva.getFechaFinal());
        assertEquals(720, reserva.getValor());
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

    @Test
    void deberiaCalcularValorCorrectoSinFinesDeSemanaParaGamAlta() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(5);
        // act
        Reserva reserva = new ReservaTestDataBuilder()
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaFinal)
                .build();

        assertEquals(500, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoSinFinesDeSemanaParaGamMedia() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(2);
        Carro carro = new Carro(1L, "", 0, "", EnumGama.MEDIA);
        // act
        Reserva reserva = new ReservaTestDataBuilder()
                .conCarro(carro)
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaFinal)
                .build();

        assertEquals(140, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoSinFinesDeSemanaParaGamBaja() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(3);
        Carro carro = new Carro(1L, "", 0, "", EnumGama.BAJA);
        // act
        Reserva reserva = new ReservaTestDataBuilder()
                .conCarro(carro)
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaFinal)
                .build();

        assertEquals(150, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoConFinesDeSemanaParaGamAlta() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(7);
        // act
        Reserva reserva = new ReservaTestDataBuilder()
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaFinal)
                .build();

        assertEquals(720, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoConFinesDeSemanaParaGamMedia() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(7);
        Carro carro = new Carro(1L, "", 0, "", EnumGama.MEDIA);
        // act
        Reserva reserva = new ReservaTestDataBuilder()
                .conCarro(carro)
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaFinal)
                .build();

        assertEquals(504, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoConFinesDeSemanaParaGamBaja() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(6);
        Carro carro = new Carro(1L, "", 0, "", EnumGama.BAJA);
        // act
        Reserva reserva = new ReservaTestDataBuilder()
                .conCarro(carro)
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaFinal)
                .build();

        assertEquals(305, reserva.getValor());

    }

    private LocalDateTime obtenerFechaProximoLunes() {
        LocalDateTime fecha = LocalDateTime.now();
        while (!fecha.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            fecha = fecha.plusDays(1);
        }
        return fecha;
    }

}