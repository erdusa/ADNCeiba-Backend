package com.ceiba.reserva.adaptador.dao;

import com.ceiba.carro.adaptador.dao.MapeoCarro;
import com.ceiba.carro.enums.EnumGama;
import com.ceiba.carro.modelo.dto.DtoCarro;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DaoReservaPostgreSQL implements DaoReserva {

    @SqlStatement(namespace = "reserva", value = "listarPorCliente.sql")
    private static String sqlListarPorCliente;
    @SqlStatement(namespace = "reserva", value = "listarCarrosDisponibles")
    private static String sqlListarCarrosDisponibles;

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoReservaPostgreSQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoReserva> listarPorCliente(Long idCliente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idCliente", idCliente);

        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarPorCliente, paramSource, new MapeoReserva());
    }

    @Override
    public List<DtoCarro> listarCarrosDisponibles(LocalDate fechaInicial, LocalDate fechaFinal, EnumGama gama) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fechainicial", fechaInicial);
        paramSource.addValue("fechafinal", fechaFinal);
        paramSource.addValue("gama", gama.toString());

        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarCarrosDisponibles, paramSource, new MapeoCarro());
    }
}
