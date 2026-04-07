package br.com.atividade.AtividadeStreamSpring.consultasAPI;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
    <T> List<T> obterDadosEmLista(String json, Class<T> classe);
}
