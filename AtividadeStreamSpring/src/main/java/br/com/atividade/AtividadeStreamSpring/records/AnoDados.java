package br.com.atividade.AtividadeStreamSpring.records;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AnoDados(@JsonAlias("code") String codigoAno,
                       @JsonAlias("name") String nomeAno) {
}
