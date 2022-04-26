package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

public class ServicioCrearReserva {

    public static final String EL_CARRO_YA_ESTA_RESERVADO_PARA_LAS_FECHAS_SELECCIONADAS = "El carro ya está reservado para las fechas seleccionadas";
    private final RepositorioReserva repositorioReserva;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public Long ejecutar(SolicitudReserva solicitudReserva) {
        Reserva reserva = Reserva.crear(solicitudReserva);
        verificarSiCarroEstaReservado(reserva);
        return repositorioReserva.crear(reserva);
    }

    private void verificarSiCarroEstaReservado(Reserva reserva) {
        boolean estaReservado = repositorioReserva.verificarSiCarroEstaReservado(reserva.getCarro().getId(),
                reserva.getFechaInicial().toLocalDate(),
                reserva.getFechaFinal().toLocalDate());

        if (estaReservado) {
            throw new ExcepcionDuplicidad(EL_CARRO_YA_ESTA_RESERVADO_PARA_LAS_FECHAS_SELECCIONADAS);
        }
    }

    private double calcularValorReserva(LocalDate fechaInicial, LocalDate fechaFinal, Double valorCarro) {
        long diasReserva = DAYS.between(fechaInicial, fechaFinal);
        int diasFinesSemana = calcularFinesDeSemanaSinIncluirFechaEntrega(fechaInicial, fechaFinal);
        long diasEntreSemana = diasReserva - diasFinesSemana;

        double valorEntreSemana = valorCarro * diasEntreSemana;
        double valorFinesDeSemana = NumberUtils.sumarPorcentaje(valorCarro, PORCENTAJE_AUMENTO_FINES_SEMANA) * diasFinesSemana;

        return valorEntreSemana + valorFinesDeSemana;
    }

    private int calcularFinesDeSemanaSinIncluirFechaEntrega(LocalDate fechaInicial, LocalDate fechaFinal) {
        return DateUtils.obtenerCantidadDiaSemana(DayOfWeek.SATURDAY, fechaInicial, fechaFinal.minusDays(1))
                + DateUtils.obtenerCantidadDiaSemana(DayOfWeek.SUNDAY, fechaInicial, fechaFinal.minusDays(1));
    }
}
