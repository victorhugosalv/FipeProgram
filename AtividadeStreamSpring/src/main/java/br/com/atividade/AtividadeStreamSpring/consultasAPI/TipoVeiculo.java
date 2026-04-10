package br.com.atividade.AtividadeStreamSpring.consultasAPI;

public enum TipoVeiculo {
    CARRO(1),
    MOTO(2),
    CAMINHAO(3);

    private Integer categoriaVeiculo;

    TipoVeiculo(Integer categoriaVeiculo){
        this.categoriaVeiculo = categoriaVeiculo;
    }

    public static TipoVeiculo fromInteger(Integer integer){
        for (TipoVeiculo tipoVeiculo : TipoVeiculo.values()){
            if (tipoVeiculo.getCategoriaVeiculo().equals(integer))
                return tipoVeiculo;
        }
        throw new IllegalArgumentException("Não encontramos essa caregoria de veiculo");
    }



    public Integer getCategoriaVeiculo() {
        return categoriaVeiculo;
    }

    public void setCategoriaVeiculo(Integer categoriaVeiculo) {
        this.categoriaVeiculo = categoriaVeiculo;
    }
}
