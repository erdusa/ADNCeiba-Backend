package com.ceiba.reserva.servicio;

import com.ceiba.carro.modelo.entidad.Carro;
import com.ceiba.comun.DateUtils;
import com.ceiba.comun.NumberUtils;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public class ServicioCrearReserva {

    public static final String EL_CARRO_YA_ESTA_RESERVADO_PARA_LAS_FECHAS_SELECCIONADAS = "El carro ya está reservado para las fechas seleccionadas";
    private final RepositorioReserva repositorioReserva;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public Long ejecutar(Reserva reserva) {
        Carro carro = obtenerCarroSiNoEstaReservado(reserva);

        double valorReserva = this.calcularValorReserva(reserva.getFechaInicial(), reserva.getFechaFinal(), carro.getGama().valor);
        reserva.setValor(valorReserva);

        return repositorioReserva.crear(reserva);
    }

    private Carro obtenerCarroSiNoEstaReservado(Reserva reserva) {
        Carro carro = repositorioReserva.obtenerCarroSiNoEstaReservado(reserva.getIdCarro(),
                reserva.getFechaInicial().toLocalDate(),
                reserva.getFechaFinal().toLocalDate());

        if (carro == null) {
            throw new ExcepcionDuplicidad(EL_CARRO_YA_ESTA_RESERVADO_PARA_LAS_FECHAS_SELECCIONADAS);
        }
        return carro;
    }

    private double calcularValorReserva(LocalDateTime fechaInicial, LocalDateTime fechaFinal, Double valorCarro) {
        long diasReserva = DAYS.between(fechaInicial.toLocalDate(), fechaFinal.toLocalDate());
        int diasFinesSemana = DateUtils.obtenerCantidadDiaSemana(DayOfWeek.SATURDAY, fechaInicial, fechaFinal)
                + DateUtils.obtenerCantidadDiaSemana(DayOfWeek.SUNDAY, fechaInicial, fechaFinal);
        long diasEntreSemana = diasReserva - diasFinesSemana;
        double valorEntreSemana = valorCarro * diasEntreSemana;
        double valorFinesDeSemana = NumberUtils.incrementarPorcentajeAValor(valorCarro, 10) * diasFinesSemana;
        return valorEntreSemana + valorFinesDeSemana;
    }
}
