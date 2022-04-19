package com.ceiba.reserva.modelo.entidad;

import com.ceiba.reserva.enums.EnumEstadoReserva;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.*;
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
    public static final String DEBE_INGRESAR_EL_VALOR = "Debe ingresar el valor";
    public static final String EL_VALOR_DEBE_SER_MAYOR_QUE_CERO = "El valor debe ser mayor que cero";
    public static final String DEBE_INGRESAR_EL_ESTADO = "Debe ingresar el estado";
    private Long id;
    private Long idCliente;
    private Long idCarro;
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;
    private Double valor;
    private String estado;

    public Reserva(Long id, Long idCliente, Long idCarro, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        validarObligatorio(idCliente, DEBE_INGRESAR_UN_CLIENTE_EXISTENTE);
        validarObligatorio(idCarro, DEBE_SELECCIONAR_UN_CARRO_EXISTENTE);
        validarObligatorio(fechaInicial, DEBE_INGRESAR_LA_FECHA_INICIAL);
        validarMenor(LocalDateTime.now(), fechaInicial, LA_FECHA_INICIAL_DEBE_SER_MAYOR_A_LA_FECHA_ACTUAL);
        validarObligatorio(fechaFinal, DEBE_INGRESAR_LA_FECHA_FINAL);
        validarMenor(fechaInicial, fechaFinal, LA_FECHA_INICIAL_NO_PUEDE_SER_MAYOR_A_LA_FECHA_FINAL);
        validarMaximo7DiasReserva(fechaInicial, fechaFinal);

        this.id = id;
        this.idCliente = idCliente;
        this.idCarro = idCarro;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.estado = EnumEstadoReserva.VIGENTE.toString();
    }
    public void setValor(Double valor) {
        validarObligatorio(valor, DEBE_INGRESAR_EL_VALOR);
        validarPositivo(valor, EL_VALOR_DEBE_SER_MAYOR_QUE_CERO);
        this.valor = valor;
    }

    private void validarMaximo7DiasReserva(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        long diasReserva = DAYS.between(fechaInicial, fechaFinal);
        validarMenor(diasReserva, 7L, NO_SE_PUEDE_HACER_LA_RESERVA_POR_MAS_DE_7_DIAS);
    }


}
