package com.ceiba.cliente.adaptador.repositorio;

import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MapeoCliente implements RowMapper<Cliente>, MapperResult {
    @Override
    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String documento = rs.getString("documento");
        String nombre = rs.getString("nombre");

        return new Cliente(id, documento, nombre);
    }
}
