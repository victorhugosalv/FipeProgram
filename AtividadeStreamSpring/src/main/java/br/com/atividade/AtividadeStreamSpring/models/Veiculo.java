package br.com.atividade.AtividadeStreamSpring.models;

import br.com.atividade.AtividadeStreamSpring.records.PrecoFipeDados;
import br.com.atividade.AtividadeStreamSpring.records.VeiculoFipeDados;

import java.util.ArrayList;
import java.util.List;

public class Veiculo {
    private String marca;
    private String codigoFipe;
    private String combustivel;
    private String modelo;
    private Integer ano;
    private String preco;
    private List<PrecoFipeDados> historicoPreco;
    private String mesReferencia;
    private Integer codigoTipoVeiculo;
    private Character tipoCombustivel;

    public Veiculo(){}

    public Veiculo(VeiculoFipeDados veiculoFipeDados){
        this.marca = veiculoFipeDados.marca();
        this.codigoFipe = veiculoFipeDados.codigoFipe();
        this.combustivel = veiculoFipeDados.combustivel();
        this.modelo = veiculoFipeDados.modelo();
        this.ano = veiculoFipeDados.ano();
        this.preco = veiculoFipeDados.preco();
        if (veiculoFipeDados.precoHistorico() !=  null){
            this.historicoPreco = veiculoFipeDados.precoHistorico();
        } else {
        }
        this.mesReferencia = veiculoFipeDados.mesReferencia();
        this.codigoTipoVeiculo = veiculoFipeDados.tipoVeiculo();
        this.tipoCombustivel = veiculoFipeDados.tipoCombustivel();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigoFipe() {
        return codigoFipe;
    }

    public void setCodigoFipe(String codigoFipe) {
        this.codigoFipe = codigoFipe;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public List<PrecoFipeDados> getHistoricoPreco() {
        return historicoPreco;
    }

    public void setHistoricoPreco(List<PrecoFipeDados> historicoPreco) {
        this.historicoPreco = historicoPreco;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public Integer getCodigoTipoVeiculo() {
        return codigoTipoVeiculo;
    }

    public void setCodigoTipoVeiculo(Integer codigoTipoVeiculo) {
        this.codigoTipoVeiculo = codigoTipoVeiculo;
    }

    public Character getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(Character tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    @Override
    public String toString() {
        return  "marca='" + marca + '\'' +
                ", codigoFipe='" + codigoFipe + '\'' +
                ", combustivel='" + combustivel + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", preco=" + preco +
                ", historicoPreco=" + historicoPreco +
                ", mesReferencia='" + mesReferencia + '\'' +
                ", codigoTipoVeiculo=" + codigoTipoVeiculo +
                ", tipoCombustivel=" + tipoCombustivel;
    }
}
