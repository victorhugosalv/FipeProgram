package br.com.atividade.AtividadeStreamSpring.records;

import com.fasterxml.jackson.annotation.JsonAlias;

public record PrecoFipeDados(@JsonAlias("month") String mes,
                             @JsonAlias("price") String preco,
                             @JsonAlias("reference") String referencia) {
}
