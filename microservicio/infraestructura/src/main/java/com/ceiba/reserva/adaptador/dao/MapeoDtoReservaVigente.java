package com.ceiba.reserva.adaptador.dao;

import com.ceiba.carro.enums.EnumGama;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.dto.DtoReservaVigente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoDtoReservaVigente implements RowMapper<DtoReservaVigente>, MapperResult {
    @Override
    public DtoReservaVigente mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        LocalDateTime fechaInicial = rs.getTimestamp("fechainicial").toLocalDateTime();
        LocalDateTime fechaFinal = rs.getTimestamp("fechafinal").toLocalDateTime();
        String marca = rs.getString("marca");
        Integer modelo = rs.getInt("modelo");
        String placa = rs.getString("placa");
        EnumGama gama = EnumGama.valueOf(rs.getString("gama"));
        Double valor = rs.getDouble("valor");

        return new DtoReservaVigente(id, fechaInicial, fechaFinal, marca, modelo, placa, gama, valor);
    }
}
