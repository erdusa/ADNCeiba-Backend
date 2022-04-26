package com.ceiba.reserva.modelo.entidad;

import com.ceiba.carro.modelo.entidad.Carro;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.comun.DateUtils;
import com.ceiba.comun.NumberUtils;
import com.ceiba.reserva.enums.EnumEstadoReserva;
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
    public static final int PORCENTAJE_AUMENTO_FINES_SEMANA = 10;

    private Long id;
    private Cliente cliente;
    private Carro carro;
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;
    private Double valor;
    private EnumEstadoReserva estado;

    private Reserva(Long id, Cliente cliente, Carro carro, LocalDateTime fechaInicial, LocalDateTime fechaFinal, Double valor, EnumEstadoReserva estado) {

        this.id = id;
        this.cliente = cliente;
        this.carro = carro;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.valor = valor;
        this.estado = estado;
    }

    public static Reserva crear(SolicitudReserva solicitudReserva) {

        validarObligatorio(solicitudReserva.getCliente(), DEBE_INGRESAR_UN_CLIENTE_EXISTENTE);
        validarObligatorio(solicitudReserva.getCliente().getId(), DEBE_INGRESAR_UN_CLIENTE_EXISTENTE);
        validarObligatorio(solicitudReserva.getCarro(), DEBE_SELECCIONAR_UN_CARRO_EXISTENTE);
        validarObligatorio(solicitudReserva.getCarro().getId(), DEBE_SELECCIONAR_UN_CARRO_EXISTENTE);
        validarObligatorio(solicitudReserva.getFechaInicial(), DEBE_INGRESAR_LA_FECHA_INICIAL);
        validarMenor(LocalDateTime.now(), solicitudReserva.getFechaInicial(), LA_FECHA_INICIAL_DEBE_SER_MAYOR_A_LA_FECHA_ACTUAL);
        validarObligatorio(solicitudReserva.getFechaFinal(), DEBE_INGRESAR_LA_FECHA_FINAL);
        validarMenor(solicitudReserva.getFechaInicial(), solicitudReserva.getFechaFinal(), LA_FECHA_INICIAL_NO_PUEDE_SER_MAYOR_A_LA_FECHA_FINAL);
        validarMaximo7DiasReserva(solicitudReserva.getFechaInicial(), solicitudReserva.getFechaFinal());

        Long id = 0L;
        Double valor = calcularValorReserva(
                solicitudReserva.getCarro().getGama().valor,
                solicitudReserva.getFechaInicial(),
                solicitudReserva.getFechaFinal()
        );

        EnumEstadoReserva estado = EnumEstadoReserva.VIGENTE;

        return new Reserva(
                id,
                solicitudReserva.getCliente(),
                solicitudReserva.getCarro(),
                solicitudReserva.getFechaInicial(),
                solicitudReserva.getFechaFinal(),
                valor,
                estado
        );
    }

    public static Reserva reconstruir(Long id, Cliente cliente, Carro carro, LocalDateTime fechaInicial,
                                      LocalDateTime fechaFinal, Double valor, EnumEstadoReserva estado) {
        return new Reserva(id, cliente, carro, fechaInicial, fechaFinal, valor, estado);
    }

    private static double calcularValorReserva(Double valorCarro, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        long diasReserva = DAYS.between(fechaInicial, fechaFinal);
        int diasFinesSemana = calcularFinesDeSemanaSinIncluirFechaEntrega(fechaInicial, fechaFinal);
        long diasEntreSemana = diasReserva - diasFinesSemana;

        double valorEntreSemana = valorCarro * diasEntreSemana;
        double valorFinesDeSemana = NumberUtils.sumarPorcentaje(valorCarro, PORCENTAJE_AUMENTO_FINES_SEMANA) * diasFinesSemana;

        return valorEntreSemana + valorFinesDeSemana;
    }

    private static int calcularFinesDeSemanaSinIncluirFechaEntrega(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        return DateUtils.obtenerCantidadDiaSemana(
                DayOfWeek.SATURDAY,
                fechaInicial.toLocalDate(),
                fechaFinal.toLocalDate().minusDays(1)
        )
                + DateUtils.obtenerCantidadDiaSemana(
                DayOfWeek.SUNDAY,
                fechaInicial.toLocalDate(),
                fechaFinal.toLocalDate().minusDays(1)
        );
    }

    private static void validarMaximo7DiasReserva(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        long diasReserva = DAYS.between(fechaInicial, fechaFinal);
        validarMenor(diasReserva, 7L, NO_SE_PUEDE_HACER_LA_RESERVA_POR_MAS_DE_7_DIAS);
    }

}
