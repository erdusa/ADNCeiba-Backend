package com.ceiba.reserva.servicio;

import com.ceiba.carro.modelo.entidad.Carro;
import com.ceiba.comun.DateUtils;
import com.ceiba.comun.NumberUtils;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class ServicioCrearReserva {

    public static final String EL_CARRO_YA_ESTA_RESERVADO_PARA_LAS_FECHAS_SELECCIONADAS = "El carro ya está reservado para las fechas seleccionadas";
    private final RepositorioReserva repositorioReserva;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public Long ejecutar(Reserva reserva) {
        Carro carro = obtenerCarroSiNoEstaReservado(reserva);

        double valorReserva = this.calcularValorReserva(
                reserva.getFechaInicial().toLocalDate(),
                reserva.getFechaFinal().toLocalDate(),
                carro.getGama().valor
        );

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

    private double calcularValorReserva(LocalDate fechaInicial, LocalDate fechaFinal, Double valorCarro) {
        long diasReserva = DAYS.between(fechaInicial, fechaFinal);
        int diasFinesSemana = calcularFinesDeSemanaSinIncluirFechaEntrega(fechaInicial, fechaFinal);
        long diasEntreSemana = diasReserva - diasFinesSemana;

        double valorEntreSemana = valorCarro * diasEntreSemana;
        double valorFinesDeSemana = NumberUtils.sumarPorcentaje(valorCarro, 10) * diasFinesSemana;

        return valorEntreSemana + valorFinesDeSemana;
    }

    private int calcularFinesDeSemanaSinIncluirFechaEntrega(LocalDate fechaInicial, LocalDate fechaFinal) {
        return DateUtils.obtenerCantidadDiaSemana(DayOfWeek.SATURDAY, fechaInicial, fechaFinal.minusDays(1))
                + DateUtils.obtenerCantidadDiaSemana(DayOfWeek.SUNDAY, fechaInicial, fechaFinal.minusDays(1));
    }
}
