package com.ceiba.cliente.adaptador.repositorio;

import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class RepositorioClientePostgreSQL implements RepositorioCliente {

    @SqlStatement(namespace = "cliente", value = "consultar")
    private static String sqlConsultarCliente;
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioClientePostgreSQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Cliente consultar(Long idCliente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", idCliente);

        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultarCliente, paramSource, new MapeoCliente());
    }
}
