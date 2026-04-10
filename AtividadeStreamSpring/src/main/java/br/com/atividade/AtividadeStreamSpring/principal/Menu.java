package br.com.atividade.AtividadeStreamSpring.principal;

import br.com.atividade.AtividadeStreamSpring.consultasAPI.TipoVeiculo;
import br.com.atividade.AtividadeStreamSpring.institutoFIPE.InstitutoFIPE;
import br.com.atividade.AtividadeStreamSpring.models.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

@Component
public class Menu {

    private final Scanner scan;
    private final InstitutoFIPE institutoFIPE;

    @Autowired
    public Menu(InstitutoFIPE institutoFIPE) {
        this.scan = new Scanner(System.in);
        this.institutoFIPE = institutoFIPE;
    }

    public void rodarPrograma() {
        boolean continuarRodando = true;

        while (continuarRodando) {
            try {
                exibirMenu();
                int opcao = Integer.parseInt(scan.nextLine());

                switch (opcao) {
                    case 1 :
                        System.out.println(buscarVeiculoCompleto().exibirResumo());
                    case 2 :
                        System.out.println(buscarVeiculoCompleto());
                    case 3:
                        //TODO
                        break;
                    case 4:
                        continuarRodando = false;
                        break;
                    default:
                        throw new IllegalStateException("Opção invalida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite apenas números.");
            } catch (IllegalArgumentException | NoSuchElementException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
    }


    //Métodos Do Switch

    public Integer exibirEscolhaMarca(TipoVeiculo tipo) {
        mostrarListas(institutoFIPE.obterMarcas(tipo), m -> "Cód: " + m.codigo() + " | Marca: " + m.nome());
        mostrarMensagem("Digite o nome da marca que você busca: ");
        String escolha = scan.nextLine();

        return institutoFIPE.buscarCodigoDaMarca(institutoFIPE.obterMarcas(tipo), escolha);
    }


    public Integer exibirEscolhaModelo(TipoVeiculo tipoVeiculo, Integer codigoMarca) {
        mostrarListas(institutoFIPE.obterModelos(tipoVeiculo,codigoMarca), m -> "Cód: " + m.codigo() + " | Modelo: " + m.nome());
        mostrarMensagem("Digite o número do modelo que você busca: ");
        return Integer.parseInt(scan.nextLine());
    }

    public String exibirAnosPorModelo(TipoVeiculo tipoVeiculo, Integer codigoMarca, Integer codigoModelo) {
        mostrarListas(institutoFIPE.obterAnosPeloModelo(tipoVeiculo,codigoMarca,codigoModelo), m -> "Cód: " + m.codigoAno() + " | Ano e Combustível: " + m.nomeAno());
        mostrarMensagem("Digite o número do código de ano que você deseja ver os modelos: ");
        return scan.nextLine();
    }

    public String exibirAnosPelaMarca(TipoVeiculo tipoVeiculo, Integer codigoMarca) {
        mostrarListas(institutoFIPE.obterAnosPelaMarca(tipoVeiculo,codigoMarca), m -> "Cód: " + m.codigoAno() + " | Ano e Combustível: " + m.nomeAno());
        mostrarMensagem("Digite o número do código de ano que você deseja ver os modelos: ");
        return scan.nextLine();
    }

    public Integer exibirModelosPelaMarcaEAno(TipoVeiculo tipoVeiculo, Integer codigoMarca, String codigoAno) {
        mostrarListas(institutoFIPE.obterModelosPelaMarcaEAno(tipoVeiculo,codigoMarca,codigoAno), m -> "Cód: " + m.codigo() + " | Modelo: " + m.nome());
        mostrarMensagem("Digite o número do código do modelo que você deseja ver a FIPE: ");
        return Integer.parseInt(scan.nextLine());
    }

    public Veiculo exibirFipeDeUmVeiculo(TipoVeiculo tipoVeiculo, Integer codigoMarca, Integer codigoModelo, String codigoAno) {
        return institutoFIPE.obterFipeDeUmVeiculo(tipoVeiculo, codigoMarca, codigoModelo, codigoAno);
    }

    private Veiculo buscarVeiculoCompleto() {
        var tipo = exibirEscolhaTipoVeiculo();
        var marca = exibirEscolhaMarca(tipo);
        var modelo = exibirEscolhaModelo(tipo, marca);
        var ano = exibirAnosPorModelo(tipo, marca, modelo);
        return exibirFipeDeUmVeiculo(tipo, marca, modelo, ano);
    }

    //Métodos do Menu

    public void exibirMenu() {

        System.out.print(
                """
                        
                        ============================================================
                                       BEM-VINDO AO BUSQUE SEU PRECO
                        ============================================================
                        1) Consultar a FIPE de um veiculo
                        2) Consultar o HISTÓRICO de FIPE de um veiculo
                        3) Comparar a FIPE de Veiculos
                        4) Sair
                        ============================================================
                        Digite qual opção lhe é interessante:
                        """
        );
    }

    public TipoVeiculo exibirEscolhaTipoVeiculo() {
        System.out.print("""
                
                =====================================================
                1)Carro
                2)Moto
                3)Caminhao
                =====================================================
                Escolha Qual Tipo de Veículo você quer consultar:
                """);
        return TipoVeiculo.fromInteger(Integer.parseInt(scan.nextLine()));
    }


    private void mostrarMensagem(String mensagem) {
        System.out.println("====================================================");
        System.out.println(mensagem);
    }

    private <T> void mostrarListas(List<T> lista, Function<T, String> formatador){
        lista.forEach(item -> System.out.println(formatador.apply(item)));
    }
}


