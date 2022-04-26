package com.ceiba.carro.adaptador.repositorio;

import com.ceiba.carro.modelo.entidad.Carro;
import com.ceiba.carro.puerto.repositorio.RepositorioCarro;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class RepositorioCarroPostgreSQL implements RepositorioCarro {

    @SqlStatement(namespace = "carro", value = "consultar")
    private static String sqlConsultarCarro;
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioCarroPostgreSQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Carro consultar(Long idCarro) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", idCarro);

        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultarCarro, paramSource, new MapeoCarro());
    }
}
