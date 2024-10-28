package com.meditrackapi.Meditrack.config;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;

@Component
public class DatabaseInit {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() throws IOException, SQLException {
        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM medicamento", Long.class);
        if (count == 0) {
            jdbcTemplate.execute(
                    "COPY medicamento(id, codigo, lote, produto, tipo, vencimento, necessita_receita) " +
                    "FROM '/data/medicamentos_v1.csv' DELIMITER ',' CSV HEADER;"
            );
            jdbcTemplate.execute(
                    "COPY posto(id, nome, bairro, rua, numero, linhas_onibus, telefone) " +
                            "FROM '/data/postos_v1.csv' DELIMITER ',' CSV HEADER;"
            );
            jdbcTemplate.execute(
                    "COPY medicamento_posto(medicamento_id, posto_id, quantidade_estoque) " +
                            "FROM '/data/medicamento_posto_v1.csv' DELIMITER ',' CSV HEADER;"
            );
        }
    }
}
