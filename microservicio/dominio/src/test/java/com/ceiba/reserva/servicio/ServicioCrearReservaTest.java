package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.carro.enums.EnumGama;
import com.ceiba.carro.modelo.entidad.Carro;
import com.ceiba.carro.modelo.servicio.testdatabuilder.CarroTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioCrearReservaTest {

    private RepositorioReserva repositorioReserva;
    private ServicioCrearReserva servicioCrearReserva;

    @BeforeEach
    void setUp() {
        repositorioReserva = Mockito.mock(RepositorioReserva.class);
        servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
    }

    @Test
    void deberiaCrearLaReservaCorrectamente() {
        //arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        Carro carro = new CarroTestDataBuilder().build();
        Mockito.when(repositorioReserva.obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any()))
                .thenReturn(carro);
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(100L);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(100L, idReserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .crear(reserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any());

    }

    @Test
    void deberiaLanzarErrorSiCarroYaEstaReservado() {
        //arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.when(repositorioReserva.obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any()))
                .thenReturn(null);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionDuplicidad.class,
                ServicioCrearReserva.EL_CARRO_YA_ESTA_RESERVADO_PARA_LAS_FECHAS_SELECCIONADAS);
        Mockito.verify(repositorioReserva, Mockito.times(0))
                .crear(reserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any());
    }

    @Test
    void deberiaCalcularValorCorrectoSinFinesDeSemanaParaGamaAlta() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(5);
        Reserva reserva = new ReservaTestDataBuilder().conFechaInicial(fechaInicial).conFechaFinal(fechaFinal).build();
        Mockito.when(repositorioReserva.obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any()))
                .thenReturn(new CarroTestDataBuilder().conGama(EnumGama.ALTA).build());
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(100L);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(100L, idReserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .crear(reserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any());

        assertEquals(500, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoSinFinesDeSemanaParaGamaMedia() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(2);
        Reserva reserva = new ReservaTestDataBuilder().conFechaInicial(fechaInicial).conFechaFinal(fechaFinal).build();
        Mockito.when(repositorioReserva.obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any()))
                .thenReturn(new CarroTestDataBuilder().conGama(EnumGama.MEDIA).build());
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(100L);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(100L, idReserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .crear(reserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any());

        assertEquals(140, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoSinFinesDeSemanaParaGamaBaja() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(3);
        Reserva reserva = new ReservaTestDataBuilder().conFechaInicial(fechaInicial).conFechaFinal(fechaFinal).build();
        Mockito.when(repositorioReserva.obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any()))
                .thenReturn(new CarroTestDataBuilder().conGama(EnumGama.BAJA).build());
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(100L);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(100L, idReserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .crear(reserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any());

        assertEquals(150, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoConFinesDeSemanaParaGamaAlta() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(7);
        Reserva reserva = new ReservaTestDataBuilder().conFechaInicial(fechaInicial).conFechaFinal(fechaFinal).build();
        Mockito.when(repositorioReserva.obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any()))
                .thenReturn(new CarroTestDataBuilder().conGama(EnumGama.ALTA).build());
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(100L);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(100L, idReserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .crear(reserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any());

        assertEquals(720, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoConFinesDeSemanaParaGamaMedia() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(7);
        Reserva reserva = new ReservaTestDataBuilder().conFechaInicial(fechaInicial).conFechaFinal(fechaFinal).build();
        Mockito.when(repositorioReserva.obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any()))
                .thenReturn(new CarroTestDataBuilder().conGama(EnumGama.MEDIA).build());
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(100L);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(100L, idReserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .crear(reserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any());

        assertEquals(504, reserva.getValor());

    }

    @Test
    void deberiaCalcularValorCorrectoConFinesDeSemanaParaGamaBaja() {
        // arrange
        LocalDateTime fechaInicial = obtenerFechaProximoLunes();
        LocalDateTime fechaFinal = fechaInicial.plusDays(6);
        Reserva reserva = new ReservaTestDataBuilder().conFechaInicial(fechaInicial).conFechaFinal(fechaFinal).build();
        Mockito.when(repositorioReserva.obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any()))
                .thenReturn(new CarroTestDataBuilder().conGama(EnumGama.BAJA).build());
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(100L);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(100L, idReserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .crear(reserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .obtenerCarroSiNoEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any());

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