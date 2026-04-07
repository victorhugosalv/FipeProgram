package br.com.atividade.AtividadeStreamSpring.records;

import com.fasterxml.jackson.annotation.JsonAlias;

public record ModeloDados(@JsonAlias("code") Integer codigo,
                          @JsonAlias("name") String nome) {
}
