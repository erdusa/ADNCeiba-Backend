package com.ceiba.cliente.adaptador.dao;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoCliente implements RowMapper<DtoCliente>, MapperResult {
    @Override
    public DtoCliente mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("clieid");
        String documento = rs.getString("cliedocumento");
        String nombre = rs.getString("clienombre");

        return new DtoCliente(id, documento, nombre);
    }
}
