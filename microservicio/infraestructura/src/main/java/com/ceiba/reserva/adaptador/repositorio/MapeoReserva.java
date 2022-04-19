package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.entidad.Reserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoReserva implements RowMapper<Reserva>, MapperResult {
    @Override
    public Reserva mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        LocalDateTime fechaInicial = rs.getTimestamp("fechainicial").toLocalDateTime();
        LocalDateTime fechaFinal = rs.getTimestamp("fechafinal").toLocalDateTime();
        Long idCliente = rs.getLong("clieid");
        Long idCarro = rs.getLong("carrid");

        return new Reserva(id, idCliente, idCarro, fechaInicial, fechaFinal);
    }
}
