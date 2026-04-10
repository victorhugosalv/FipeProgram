package br.com.atividade.AtividadeStreamSpring.service;

import br.com.atividade.AtividadeStreamSpring.utils.ConsultaAPI;
import br.com.atividade.AtividadeStreamSpring.utils.ConverteDados;
import br.com.atividade.AtividadeStreamSpring.utils.TipoVeiculo;
import br.com.atividade.AtividadeStreamSpring.models.Veiculo;
import br.com.atividade.AtividadeStreamSpring.records.AnoDados;
import br.com.atividade.AtividadeStreamSpring.records.MarcaDados;
import br.com.atividade.AtividadeStreamSpring.records.ModeloDados;
import br.com.atividade.AtividadeStreamSpring.records.VeiculoFipeDados;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;


@Component
public class InstitutoFIPE {

    private final String ENDERECO = "https://fipe.parallelum.com.br/api/v2/";

    private final ConsultaAPI consulta;
    private final ConverteDados conversor;

    public InstitutoFIPE(ConsultaAPI consulta, ConverteDados conversor) {
        this.consulta = consulta;
        this.conversor = conversor;
    }

    public List<MarcaDados> obterMarcas(TipoVeiculo tipoVeiculo){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo));
        return ordenaLista(conversor.obterDadosEmLista(json, MarcaDados.class), Comparator.comparing(MarcaDados::codigo));
    }

    public List<ModeloDados> obterModelos(TipoVeiculo tipoVeiculo, Integer codigoMarca){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/models");
        return ordenaLista(conversor.obterDadosEmLista(json, ModeloDados.class), Comparator.comparing(ModeloDados::codigo));
    }

    public List<AnoDados> obterAnosPeloModelo(TipoVeiculo tipoVeiculo, Integer codigoMarca, Integer codigoModelo){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/models/" + codigoModelo + "/years");
        return ordenaLista(conversor.obterDadosEmLista(json, AnoDados.class), Comparator.comparing(AnoDados::codigoAno));
    }

    public List<AnoDados> obterAnosPelaMarca(TipoVeiculo tipoVeiculo, Integer codigoMarca){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/years");
        return ordenaLista(conversor.obterDadosEmLista(json, AnoDados.class), Comparator.comparing(AnoDados::codigoAno));
    }

    public List<ModeloDados> obterModelosPelaMarcaEAno(TipoVeiculo tipoVeiculo, Integer codigoMarca, String codigoAno){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/years/" + codigoAno + "/models");
        return ordenaLista(conversor.obterDadosEmLista(json, ModeloDados.class), Comparator.comparing(ModeloDados::codigo));
    }

    public Veiculo obterFipeDeUmVeiculo(TipoVeiculo tipoVeiculo, Integer codigoMarca, Integer codigoModelo, String codigoAno){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/models/" + codigoModelo + "/years/" + codigoAno);
        VeiculoFipeDados veiculoFipeDados = conversor.obterDados(json, VeiculoFipeDados.class);
        return new Veiculo(veiculoFipeDados);
    }

    public Integer buscarCodigoDaMarca(List<MarcaDados> marcas, String nomeMarca){
        return marcas.stream()
                .filter(m -> m.nome().toUpperCase().contains(nomeMarca.toUpperCase()))
                .findFirst()
                .map(MarcaDados::codigo)
                .orElseThrow(() -> new IllegalArgumentException("Marca não encontrada"));
    }

    //Metodo Complementar

    public String complementingURL(TipoVeiculo tipoVeiculo){
        String complementoUrl = switch (tipoVeiculo) {
            case CARRO -> "cars/brands";
            case MOTO -> "motorcycles/brands";
            case CAMINHAO -> "trucks/brands";
        };
        return complementoUrl;
    }

    private <T> List<T> ordenaLista(List<T> lista, Comparator<T> comparator){
        return lista.stream().sorted(comparator).toList();
    }


}