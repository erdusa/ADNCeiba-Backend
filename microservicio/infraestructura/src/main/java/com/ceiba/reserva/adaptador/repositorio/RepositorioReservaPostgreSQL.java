package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.entidad.Reserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class RepositorioReservaPostgreSQL implements com.ceiba.reserva.puerto.repositorio.RepositorioReserva {

    @SqlStatement(namespace = "reserva", value = "crear.sql")
    private static String sqlCrear;
    @SqlStatement(namespace = "reserva", value = "cancelar.sql")
    private static String sqlCancelar;
    @SqlStatement(namespace = "reserva", value = "consultar")
    private static String sqlConsultar;
    @SqlStatement(namespace = "reserva", value = "verificarSiCarroEstaReservado")
    private static String sqlVerificarSiCarroEstaReservado;

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioReservaPostgreSQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Reserva reserva) {
        return this.customNamedParameterJdbcTemplate.crear(reserva, sqlCrear);
    }

    @Override
    public void cancelar(Reserva reserva) {
        this.customNamedParameterJdbcTemplate.actualizar(reserva, sqlCancelar);
    }

    @Override
    public Reserva consultar(Long idReserva) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", idReserva);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultar, paramSource, Reserva.class);
    }

    @Override
    public boolean verificarSiCarroEstaReservado(Long idCarro, LocalDate fechaInicial, LocalDate fechaFinal) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idCarro", idCarro);
        paramSource.addValue("fechainicial", fechaInicial);
        paramSource.addValue("fechafinal", fechaFinal);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlVerificarSiCarroEstaReservado, paramSource, Boolean.class);
    }
}
