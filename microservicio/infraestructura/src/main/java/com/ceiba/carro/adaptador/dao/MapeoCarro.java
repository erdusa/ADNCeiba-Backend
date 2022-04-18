package com.ceiba.carro.adaptador.dao;

import com.ceiba.carro.enums.EnumGama;
import com.ceiba.carro.modelo.dto.DtoCarro;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoCarro implements RowMapper<DtoCarro>, MapperResult {
    @Override
    public DtoCarro mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("carrid");
        String marca = rs.getString("carrmarca");
        Integer modelo = rs.getInt("carrmodelo");
        String placa = rs.getString("carrplaca");
        EnumGama gama = EnumGama.valueOf(rs.getString("carrgama"));

        return new DtoCarro(id, marca, modelo, placa, gama);
    }
}
