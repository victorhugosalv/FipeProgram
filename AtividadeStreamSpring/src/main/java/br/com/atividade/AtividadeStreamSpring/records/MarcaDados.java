package br.com.atividade.AtividadeStreamSpring.records;

import com.fasterxml.jackson.annotation.JsonAlias;

public record MarcaDados(@JsonAlias("code") Integer codigo,
                         @JsonAlias("name") String nome) {
}
