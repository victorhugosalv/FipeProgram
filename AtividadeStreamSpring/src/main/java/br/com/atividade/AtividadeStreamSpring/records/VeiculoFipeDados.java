package br.com.atividade.AtividadeStreamSpring.records;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record VeiculoFipeDados(@JsonAlias("brand") String marca,
                               @JsonAlias("codeFipe") String codigoFipe,
                               @JsonAlias("fuel") String combustivel,
                               @JsonAlias("model") String modelo,
                               @JsonAlias("modelYear") Integer ano,
                               @JsonAlias("price") String preco,
                               @JsonAlias("priceHistory") List<PrecoFipeDados> precoHistorico,
                               @JsonAlias("referenceMonth") String mesReferencia,
                               @JsonAlias("vehicleType") Integer tipoVeiculo,
                               @JsonAlias("fuelAcronym") Character tipoCombustivel)
{}
