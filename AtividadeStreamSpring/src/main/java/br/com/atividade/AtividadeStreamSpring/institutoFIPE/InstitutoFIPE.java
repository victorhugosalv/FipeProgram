package br.com.atividade.AtividadeStreamSpring.institutoFIPE;

import br.com.atividade.AtividadeStreamSpring.consultasAPI.ConsultaAPI;
import br.com.atividade.AtividadeStreamSpring.consultasAPI.ConverteDados;
import br.com.atividade.AtividadeStreamSpring.consultasAPI.TIPO_VEICULO;
import br.com.atividade.AtividadeStreamSpring.models.Veiculo;
import br.com.atividade.AtividadeStreamSpring.records.AnoDados;
import br.com.atividade.AtividadeStreamSpring.records.MarcaDados;
import br.com.atividade.AtividadeStreamSpring.records.ModeloDados;
import br.com.atividade.AtividadeStreamSpring.records.VeiculoFipeDados;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class InstitutoFIPE {

    private final String ENDERECO = "https://fipe.parallelum.com.br/api/v2/";

    private final ConsultaAPI consulta;
    private final ConverteDados conversor;

    public InstitutoFIPE() {
        this.consulta = new ConsultaAPI();
        this.conversor = new ConverteDados();
    }

    public List<MarcaDados> obterMarcas(TIPO_VEICULO tipoVeiculo){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo));
        return conversor.obterDadosEmLista(json, MarcaDados.class);
    }

    public List<ModeloDados> obterModelos(TIPO_VEICULO tipoVeiculo, Integer codigoMarca){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/models");
        return conversor.obterDadosEmLista(json, ModeloDados.class);
    }


    public List<AnoDados> obterAnosPeloModelo(TIPO_VEICULO tipoVeiculo, Integer codigoMarca, Integer codigoModelo){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/models/" + codigoModelo + "/years");
        return conversor.obterDadosEmLista(json, AnoDados.class);
    }

    public List<AnoDados> obterAnosPelaMarca(TIPO_VEICULO tipoVeiculo, Integer codigoMarca){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/years");
        return conversor.obterDadosEmLista(json, AnoDados.class);
    }

    public List<ModeloDados> obterModelosPelaMarcaEAno(TIPO_VEICULO tipoVeiculo, Integer codigoMarca, String codigoAno){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/years/" + codigoAno + "/models");
        return conversor.obterDadosEmLista(json, ModeloDados.class);
    }

    public Veiculo obterFipeDeUmVeiculo(TIPO_VEICULO tipoVeiculo, Integer codigoMarca, Integer codigoModelo, String codigoAno){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/models/" + codigoModelo + "/years/" + codigoAno);
        VeiculoFipeDados veiculoFipeDados = conversor.obterDados(json, VeiculoFipeDados.class);
        return new Veiculo(veiculoFipeDados);
    }

    //Metodo Complementar

    public String complementingURL(TIPO_VEICULO tipoVeiculo){
        String complementoUrl = switch (tipoVeiculo) {
            case CARRO -> "cars/brands";
            case MOTO -> "motorcycles/brands";
            case CAMINHAO -> "trucks/brands";
        };
        return complementoUrl;
    }
}