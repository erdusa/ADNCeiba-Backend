package com.ceiba.carro.adaptador.repositorio;

import com.ceiba.carro.enums.EnumGama;
import com.ceiba.carro.modelo.entidad.Carro;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoCarro implements RowMapper<Carro>, MapperResult {
    @Override
    public Carro mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        String marca = rs.getString("marca");
        Integer modelo = rs.getInt("modelo");
        String placa = rs.getString("placa");
        EnumGama gama = EnumGama.valueOf(rs.getString("gama"));

        return new Carro(id, marca, modelo, placa, gama);
    }
}
