package com.ceiba.reserva.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.carro.enums.EnumGama;
import com.ceiba.carro.modelo.servicio.testdatabuilder.CarroTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.enums.EnumEstadoReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservaTest {

    public static final String DEBE_INGRESAR_UN_CLIENTE_EXISTENTE = "Debe ingresar un cliente existente";
    public static final String DEBE_SELECCIONAR_UN_CARRO_EXISTENTE = "Debe seleccionar un carro existente";
    public static final String DEBE_INGRESAR_LA_FECHA_INICIAL = "Debe ingresar la fecha inicial";
    public static final String DEBE_INGRESAR_LA_FECHA_FINAL = "Debe ingresar la fecha final";
    public static final String LA_FECHA_INICIAL_DEBE_SER_MAYOR_A_LA_FECHA_ACTUAL = "La fecha inicial debe ser mayor a la fecha actual";
    public static final String LA_FECHA_INICIAL_NO_PUEDE_SER_MAYOR_A_LA_FECHA_FINAL = "La fecha inicial no puede ser mayor a la fecha final";
    public static final String NO_SE_PUEDE_HACER_LA_RESERVA_POR_MAS_DE_7_DIAS = "No se puede hacer la reserva por mas de 7 días";

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
        //assertEquals(1L, reserva.getId());
        assertEquals(1L, reserva.getCliente().getId());
        assertEquals(1L, reserva.getCarro().getId());
        assertEquals(fechaInicial, reserva.getFechaInicial());
        assertEquals(fechaInicial.plusDays(1), reserva.getFechaFinal());
        assertEquals(EnumEstadoReserva.VIGENTE, reserva.getEstado());
    }

    @Test
    void deberiaFallarSinCliente() {
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conCliente(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, DEBE_INGRESAR_UN_CLIENTE_EXISTENTE);
    }

    @Test
    void deberiaFallarSinCarro() {
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conCarro(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, DEBE_SELECCIONAR_UN_CARRO_EXISTENTE);
    }

    @Test
    void deberiaFallarSinFechaInicial() {
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaInicial(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, DEBE_INGRESAR_LA_FECHA_INICIAL);
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
                ExcepcionValorInvalido.class, LA_FECHA_INICIAL_DEBE_SER_MAYOR_A_LA_FECHA_ACTUAL);
    }

    @Test
    void deberiaFallarSinFechaFinal() {
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaFinal(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, DEBE_INGRESAR_LA_FECHA_FINAL);
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
                ExcepcionValorInvalido.class, LA_FECHA_INICIAL_NO_PUEDE_SER_MAYOR_A_LA_FECHA_FINAL);
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
        //assertEquals(1L, reserva.getId());
        assertEquals(1L, reserva.getCliente().getId());
        assertEquals(1L, reserva.getCarro().getId());
        assertEquals(fechaInicial, reserva.getFechaInicial());
        assertEquals(fechaFinal, reserva.getFechaFinal());
        assertEquals(EnumEstadoReserva.VIGENTE, reserva.getEstado());
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
                ExcepcionValorInvalido.class, NO_SE_PUEDE_HACER_LA_RESERVA_POR_MAS_DE_7_DIAS);
    }

    @Test
    void deberiaCalcularValorCorrectoSinFinesDeSemanaParaGamaAlta() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(5);
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder()
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaFinal)
                .conCarro(new CarroTestDataBuilder().conGama(EnumGama.ALTA).build());
        // act
        Reserva reserva = reservaTestDataBuilder.build();
        // assert
        assertEquals(500, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoSinFinesDeSemanaParaGamaMedia() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(2);
        // act
        Reserva reserva = new ReservaTestDataBuilder()
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaFinal)
                .conCarro(new CarroTestDataBuilder().conGama(EnumGama.MEDIA).build())
                .build();
        // assert
        assertEquals(140, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoSinFinesDeSemanaParaGamaBaja() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(3);
        // act
        Reserva reserva = new ReservaTestDataBuilder()
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaFinal)
                .conCarro(new CarroTestDataBuilder().conGama(EnumGama.BAJA).build()).build();
        // assert
        assertEquals(150, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoConFinesDeSemanaParaGamaAlta() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(7);
        // act
        Reserva reserva = new ReservaTestDataBuilder()
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaFinal)
                .conCarro(new CarroTestDataBuilder().conGama(EnumGama.ALTA).build())
                .build();
        // assert
        assertEquals(720, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoConFinesDeSemanaParaGamaMedia() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(7);
        // act
        Reserva reserva = new ReservaTestDataBuilder()
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaFinal)
                .conCarro(new CarroTestDataBuilder().conGama(EnumGama.MEDIA).build())
                .build();
        // assert
        assertEquals(504, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoConFinesDeSemanaParaGamaBaja() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(6);
        // act
        Reserva reserva = new ReservaTestDataBuilder()
                .conFechaInicial(fechaInicial)
                .conFechaFinal(fechaFinal)
                .conCarro(new CarroTestDataBuilder().conGama(EnumGama.BAJA).build())
                .build();
        // assert
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