package br.com.atividade.AtividadeStreamSpring.institutoFIPE;

import br.com.atividade.AtividadeStreamSpring.consultasAPI.ConsultaAPI;
import br.com.atividade.AtividadeStreamSpring.consultasAPI.ConverteDados;
import br.com.atividade.AtividadeStreamSpring.consultasAPI.TipoVeiculo;
import br.com.atividade.AtividadeStreamSpring.models.Veiculo;
import br.com.atividade.AtividadeStreamSpring.records.AnoDados;
import br.com.atividade.AtividadeStreamSpring.records.MarcaDados;
import br.com.atividade.AtividadeStreamSpring.records.ModeloDados;
import br.com.atividade.AtividadeStreamSpring.records.VeiculoFipeDados;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InstitutoFIPE {

    private final String ENDERECO = "https://fipe.parallelum.com.br/api/v2/";

    private final ConsultaAPI consulta;
    private final ConverteDados conversor;

    public InstitutoFIPE() {
        this.consulta = new ConsultaAPI();
        this.conversor = new ConverteDados();
    }

    public List<MarcaDados> obterMarcas(TipoVeiculo tipoVeiculo){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo));
        return conversor.obterDadosEmLista(json, MarcaDados.class);
    }

    public List<ModeloDados> obterModelos(TipoVeiculo tipoVeiculo, Integer codigoMarca){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/models");
        return conversor.obterDadosEmLista(json, ModeloDados.class);
    }


    public List<AnoDados> obterAnosPeloModelo(TipoVeiculo tipoVeiculo, Integer codigoMarca, Integer codigoModelo){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/models/" + codigoModelo + "/years");
        return conversor.obterDadosEmLista(json, AnoDados.class);
    }

    public List<AnoDados> obterAnosPelaMarca(TipoVeiculo tipoVeiculo, Integer codigoMarca){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/years");
        return conversor.obterDadosEmLista(json, AnoDados.class);
    }

    public List<ModeloDados> obterModelosPelaMarcaEAno(TipoVeiculo tipoVeiculo, Integer codigoMarca, String codigoAno){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/years/" + codigoAno + "/models");
        return conversor.obterDadosEmLista(json, ModeloDados.class);
    }

    public Veiculo obterFipeDeUmVeiculo(TipoVeiculo tipoVeiculo, Integer codigoMarca, Integer codigoModelo, String codigoAno){
        var json = consulta.obterDados(ENDERECO + complementingURL(tipoVeiculo) + "/" + codigoMarca + "/models/" + codigoModelo + "/years/" + codigoAno);
        VeiculoFipeDados veiculoFipeDados = conversor.obterDados(json, VeiculoFipeDados.class);
        return new Veiculo(veiculoFipeDados);
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
}