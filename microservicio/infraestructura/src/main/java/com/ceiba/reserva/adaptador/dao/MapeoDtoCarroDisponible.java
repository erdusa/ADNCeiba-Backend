package com.ceiba.reserva.adaptador.dao;

import com.ceiba.carro.enums.EnumGama;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.dto.DtoCarroDisponible;
import com.ceiba.reserva.shared.CalcularValorReserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoDtoCarroDisponible implements RowMapper<DtoCarroDisponible>, MapperResult {
    @Override
    public DtoCarroDisponible mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        String marca = rs.getString("marca");
        Integer modelo = rs.getInt("modelo");
        String placa = rs.getString("placa");
        EnumGama gama = EnumGama.valueOf(rs.getString("gama"));
        LocalDateTime fechaInicial = rs.getTimestamp("fechainicial").toLocalDateTime();
        LocalDateTime fechaFinal = rs.getTimestamp("fechafinal").toLocalDateTime();

        Double valor = CalcularValorReserva.ejecutar(gama.valor, fechaInicial, fechaFinal);

        return new DtoCarroDisponible(fechaInicial, fechaFinal, id, marca, modelo, placa, valor);
    }
}
