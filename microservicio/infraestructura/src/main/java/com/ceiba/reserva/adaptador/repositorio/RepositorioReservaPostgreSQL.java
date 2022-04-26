package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class RepositorioReservaPostgreSQL implements RepositorioReserva {

    public static final String EL_CARRO_NO_ESTA_DISPONIBLE = "El carro no está disponible";
    @SqlStatement(namespace = "reserva", value = "crear.sql")
    private static String sqlCrear;
    @SqlStatement(namespace = "reserva", value = "cancelar.sql")
    private static String sqlCancelar;
    @SqlStatement(namespace = "reserva", value = "consultar")
    private static String sqlConsultar;
    @SqlStatement(namespace = "reserva", value = "verificarSiCarroEstaReservado")
    private static String sqlverificarSiCarroEstaReservado;
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoReserva mapeoReserva;

    public RepositorioReservaPostgreSQL(
            CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate,
            MapeoReserva mapeoReserva) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoReserva = mapeoReserva;
    }

    @Override
    public Long crear(Reserva reserva) {
        return customNamedParameterJdbcTemplate.crear(sqlCrear, MapeoReserva.getParameters(reserva));
    }

    @Override
    public void cancelar(Reserva reserva) {
        this.customNamedParameterJdbcTemplate.actualizar(reserva, sqlCancelar);
    }

    @Override
    public Reserva consultar(Long idReserva) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", idReserva);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(
                sqlConsultar,
                paramSource,
                mapeoReserva
        );
    }

    @Override
    public boolean verificarSiCarroEstaReservado(Long idCarro, LocalDate fechaInicial, LocalDate fechaFinal) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idCarro", idCarro);
        paramSource.addValue("fechainicial", fechaInicial);
        paramSource.addValue("fechafinal", fechaFinal);
        return Boolean.TRUE.equals(this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlverificarSiCarroEstaReservado, paramSource, Boolean.class));
    }
}
