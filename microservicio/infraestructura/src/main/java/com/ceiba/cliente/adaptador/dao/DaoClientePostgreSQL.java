package com.ceiba.cliente.adaptador.dao;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import com.ceiba.infraestructura.excepcion.ExcepcionTecnica;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class DaoClientePostgreSQL implements DaoCliente {

    public static final String NO_EXISTE_UN_CLIENTE_CON_ESE_DOCUMENTO = "No existe un cliente con ese documento";
    @SqlStatement(namespace = "cliente", value = "consultarPorDocumento")
    private static String sqlConsultarCliente;
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoClientePostgreSQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public DtoCliente consultarPorDocumento(String documento) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("documento", documento);
        try {
            return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultarCliente, paramSource, new MapeoDtoCliente());
        } catch (EmptyResultDataAccessException e) {
            throw new ExcepcionTecnica(NO_EXISTE_UN_CLIENTE_CON_ESE_DOCUMENTO);
        }
    }
}
