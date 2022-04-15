package com.ceiba.reserva.modelo.entidad;

import com.ceiba.carro.modelo.entidad.Carro;
import com.ceiba.cliente.modelo.entidad.Cliente;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarMenor;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static java.time.temporal.ChronoUnit.DAYS;

@Getter
public class Reserva {

    public static final String DEBE_INGRESAR_UN_CLIENTE_EXISTENTE = "Debe ingresar un cliente existente";
    public static final String DEBE_SELECCIONAR_UN_CARRO_EXISTENTE = "Debe seleccionar un carro existente";
    public static final String DEBE_INGRESAR_LA_FECHA_INICIAL = "Debe ingresar la fecha inicial";
    public static final String DEBE_INGRESAR_LA_FECHA_FINAL = "Debe ingresar la fecha final";
    public static final String LA_FECHA_INICIAL_DEBE_SER_MAYOR_A_LA_FECHA_ACTUAL = "La fecha inicial debe ser mayor a la fecha actual";
    public static final String LA_FECHA_INICIAL_NO_PUEDE_SER_MAYOR_A_LA_FECHA_FINAL = "La fecha inicial no puede ser mayor a la fecha final";
    public static final String NO_SE_PUEDE_HACER_LA_RESERVA_POR_MAS_DE_7_DIAS = "No se puede hacer la reserva por mas de 7 días";
    private Long id;
    private Cliente cliente;
    private Carro carro;
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;
    private Double valor;

    public Reserva(Long id, Cliente cliente, Carro carro, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        validarObligatorio(cliente, DEBE_INGRESAR_UN_CLIENTE_EXISTENTE);
        validarObligatorio(cliente.getId(), DEBE_INGRESAR_UN_CLIENTE_EXISTENTE);
        validarObligatorio(carro, DEBE_SELECCIONAR_UN_CARRO_EXISTENTE);
        validarObligatorio(carro.getId(), DEBE_SELECCIONAR_UN_CARRO_EXISTENTE);
        validarObligatorio(fechaInicial, DEBE_INGRESAR_LA_FECHA_INICIAL);
        validarMenor(LocalDateTime.now(), fechaInicial, LA_FECHA_INICIAL_DEBE_SER_MAYOR_A_LA_FECHA_ACTUAL);
        validarObligatorio(fechaFinal, DEBE_INGRESAR_LA_FECHA_FINAL);
        validarMenor(fechaInicial, fechaFinal, LA_FECHA_INICIAL_NO_PUEDE_SER_MAYOR_A_LA_FECHA_FINAL);
        validarMaximo7DiasReserva(fechaInicial, fechaFinal);

        this.id = id;
        this.cliente = cliente;
        this.carro = carro;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.valor = this.calcularValor();
    }

    private void validarMaximo7DiasReserva(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        long diasReserva = DAYS.between(fechaInicial, fechaFinal);
        validarMenor(diasReserva, 7L, NO_SE_PUEDE_HACER_LA_RESERVA_POR_MAS_DE_7_DIAS);
    }

    private double calcularValor() {
        long diasReserva = DAYS.between(fechaInicial.toLocalDate(), fechaFinal.toLocalDate());
        long diasFinesSemana = obtenerCantidadDeFinesDeSemana(fechaInicial, fechaFinal);
        long diasEntreSemana = diasReserva - diasFinesSemana;
        double valorEntreSemana = carro.getGama().valor * diasEntreSemana;
        double valorFinesDeSemana = incrementarPorcentajeAValor(carro.getGama().valor, 10) * diasFinesSemana;
        return valorEntreSemana + valorFinesDeSemana;
    }

    private int obtenerCantidadDeFinesDeSemana(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        int diasFinesSemana = 0;
        while (fechaInicial.isBefore(fechaFinal)) {
            if (fechaInicial.getDayOfWeek().equals(DayOfWeek.SATURDAY) || fechaInicial.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                diasFinesSemana++;
            }
            fechaInicial = fechaInicial.plusDays(1);
        }
        return diasFinesSemana;
    }

    private double incrementarPorcentajeAValor(double valor, double porcentaje) {
        return valor + valor * porcentaje / 100;
    }

}
