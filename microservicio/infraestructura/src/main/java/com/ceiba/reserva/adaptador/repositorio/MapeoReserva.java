package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.carro.modelo.entidad.Carro;
import com.ceiba.carro.puerto.repositorio.RepositorioCarro;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.enums.EnumEstadoReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class MapeoReserva implements RowMapper<Reserva>, MapperResult {

    private final RepositorioCliente repositorioCliente;
    private final RepositorioCarro repositorioCarro;

    public MapeoReserva(RepositorioCliente repositorioCliente, RepositorioCarro repositorioCarro) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioCarro = repositorioCarro;
    }

    public static MapSqlParameterSource getParameters(Reserva reserva) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("clieid", reserva.getCliente().getId());
        paramSource.addValue("carrid", reserva.getCarro().getId());
        paramSource.addValue("fechaInicial", reserva.getFechaInicial());
        paramSource.addValue("fechaFinal", reserva.getFechaFinal());
        paramSource.addValue("valor", reserva.getValor());
        paramSource.addValue("estado", reserva.getEstado().toString());
        return paramSource;
    }

    @Override
    public Reserva mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        LocalDateTime fechaInicial = rs.getTimestamp("fechainicial").toLocalDateTime();
        LocalDateTime fechaFinal = rs.getTimestamp("fechafinal").toLocalDateTime();
        Cliente cliente = this.repositorioCliente.consultar(rs.getLong("clieid"));
        Carro carro = this.repositorioCarro.consultar(rs.getLong("carrid"));
        Double valor = rs.getDouble("valor");
        EnumEstadoReserva estado = EnumEstadoReserva.valueOf(rs.getString("estado"));

        return Reserva.reconstruir(id, cliente, carro, fechaInicial, fechaFinal, valor, estado);
    }
}
