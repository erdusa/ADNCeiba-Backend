package com.ceiba.reserva.adaptador.dao;

import com.ceiba.carro.adaptador.dao.MapeoDtoCarro;
import com.ceiba.carro.modelo.dto.DtoCarro;
import com.ceiba.cliente.adaptador.dao.MapeoDtoCliente;
import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.enums.EnumEstadoReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoDtoReserva implements RowMapper<DtoReserva>, MapperResult {
    @Override
    public DtoReserva mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        LocalDateTime fechaInicial = rs.getTimestamp("fechainicial").toLocalDateTime();
        LocalDateTime fechaFinal = rs.getTimestamp("fechafinal").toLocalDateTime();
        Double valor = rs.getDouble("valor");
        EnumEstadoReserva estado = EnumEstadoReserva.valueOf(rs.getString("estado"));

        DtoCliente dtoCliente = new MapeoDtoCliente().mapRow(rs, rowNum);
        DtoCarro dtoCarro = new MapeoDtoCarro().mapRow(rs, rowNum);

        return new DtoReserva(id, dtoCliente, dtoCarro, fechaInicial, fechaFinal, valor, estado);
    }
}
