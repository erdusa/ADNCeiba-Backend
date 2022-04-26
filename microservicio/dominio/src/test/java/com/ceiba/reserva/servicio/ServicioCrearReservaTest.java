package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.reserva.modelo.entidad.SolicitudReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.SolicitudReservaTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioCrearReservaTest {

    public static final String EL_CARRO_YA_ESTA_RESERVADO_PARA_LAS_FECHAS_SELECCIONADAS = "El carro ya está reservado para las fechas seleccionadas";

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
        SolicitudReserva solicitudReserva = new SolicitudReservaTestDataBuilder().build();
        Mockito.when(repositorioReserva.verificarSiCarroEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any()))
                .thenReturn(false);
        Mockito.when(repositorioReserva.crear(Mockito.any())).thenReturn(1L);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(solicitudReserva);
        // assert
        assertEquals(1L, idReserva);
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .crear(Mockito.any());
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .verificarSiCarroEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any());

    }

    @Test
    void deberiaLanzarErrorSiCarroYaEstaReservado() {
        //arrange
        SolicitudReserva solicitudReserva = new SolicitudReservaTestDataBuilder().build();
        Mockito.when(repositorioReserva.verificarSiCarroEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any()))
                .thenReturn(true);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(solicitudReserva), ExcepcionDuplicidad.class,
                EL_CARRO_YA_ESTA_RESERVADO_PARA_LAS_FECHAS_SELECCIONADAS);
        Mockito.verify(repositorioReserva, Mockito.times(0))
                .crear(Mockito.any());
        Mockito.verify(repositorioReserva, Mockito.times(1))
                .verificarSiCarroEstaReservado(Mockito.anyLong(), Mockito.any(), Mockito.any());
    }


}