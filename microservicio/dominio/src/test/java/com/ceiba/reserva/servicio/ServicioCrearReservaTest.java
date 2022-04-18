package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.servicio.testdatabuilder.ReservaTestDataBuilder;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        Mockito.when(repositorioReserva.verificarSiCarroEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any()))
                .thenReturn(false);
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(100L);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(100L, idReserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .crear(reserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .verificarSiCarroEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any());

    }

    @Test
    void deberiaLanzarErrorSiCarroYaEstaReservado() {
        //arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.when(repositorioReserva.verificarSiCarroEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any()))
                .thenReturn(true);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionDuplicidad.class,
                ServicioCrearReserva.EL_CARRO_YA_ESTA_RESERVADO_PARA_LAS_FECHAS_SELECCIONADAS);
        Mockito.verify(repositorioReserva, Mockito.times(0))
                .crear(reserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .verificarSiCarroEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any());
    }
}