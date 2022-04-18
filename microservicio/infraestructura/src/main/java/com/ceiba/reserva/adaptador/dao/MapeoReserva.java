package com.ceiba.reserva.adaptador.dao;

import com.ceiba.carro.adaptador.dao.MapeoCarro;
import com.ceiba.carro.modelo.dto.DtoCarro;
import com.ceiba.cliente.adaptador.dao.MapeoCliente;
import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.enums.EnumEstadoReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {
    @Override
    public DtoReserva mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("reseid");
        LocalDateTime fechaInicial = rs.getTimestamp("resefechainicial").toLocalDateTime();
        LocalDateTime fechaFinal = rs.getTimestamp("resefechafinal").toLocalDateTime();
        Double valor = rs.getDouble("resevalor");
        EnumEstadoReserva estado = EnumEstadoReserva.valueOf(rs.getString("reseestado"));

        DtoCliente dtoCliente = new MapeoCliente().mapRow(rs, rowNum);
        DtoCarro dtoCarro = new MapeoCarro().mapRow(rs, rowNum);

        return new DtoReserva(id, dtoCliente, dtoCarro, fechaInicial, fechaFinal, valor, estado);
    }
}
