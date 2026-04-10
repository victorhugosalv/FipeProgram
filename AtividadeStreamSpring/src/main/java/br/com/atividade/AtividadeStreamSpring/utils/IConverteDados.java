package br.com.atividade.AtividadeStreamSpring.utils;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
    <T> List<T> obterDadosEmLista(String json, Class<T> classe);
}
