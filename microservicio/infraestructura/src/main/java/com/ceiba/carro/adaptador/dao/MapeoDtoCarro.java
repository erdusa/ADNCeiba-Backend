package com.ceiba.carro.adaptador.dao;

import com.ceiba.carro.enums.EnumGama;
import com.ceiba.carro.modelo.dto.DtoCarro;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoDtoCarro implements RowMapper<DtoCarro>, MapperResult {
    @Override
    public DtoCarro mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        String marca = rs.getString("marca");
        Integer modelo = rs.getInt("modelo");
        String placa = rs.getString("placa");
        EnumGama gama = EnumGama.valueOf(rs.getString("gama"));

        return new DtoCarro(id, marca, modelo, placa, gama);
    }
}
