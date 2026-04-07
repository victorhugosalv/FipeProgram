package br.com.atividade.AtividadeStreamSpring.principal;

import br.com.atividade.AtividadeStreamSpring.consultasAPI.TipoVeiculo;
import br.com.atividade.AtividadeStreamSpring.institutoFIPE.InstitutoFIPE;
import br.com.atividade.AtividadeStreamSpring.models.Veiculo;
import br.com.atividade.AtividadeStreamSpring.records.AnoDados;
import br.com.atividade.AtividadeStreamSpring.records.MarcaDados;
import br.com.atividade.AtividadeStreamSpring.records.ModeloDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Menu {


    private Scanner scan;
    private InstitutoFIPE institutoFIPE;

    @Autowired
    public Menu(InstitutoFIPE institutoFIPE){
        this.scan = new Scanner(System.in);
        this.institutoFIPE = institutoFIPE;
    }


    public void exibirMenu(){

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
        int tipoVeiculo = Integer.parseInt(scan.nextLine());


        TipoVeiculo tipoVeic = switch (tipoVeiculo){
            case 1 -> TipoVeiculo.CARRO;
            case 2 -> TipoVeiculo.MOTO;
            case 3 -> TipoVeiculo.CAMINHAO;
            default -> null;
        };
        return tipoVeic;

    }

    public Integer exibirEscolhaMarca(TipoVeiculo tipo){

        List<MarcaDados> marcas = institutoFIPE.obterMarcas(tipo);

        marcas.stream()
                .sorted(Comparator.comparing(MarcaDados::codigo))
                .forEach(m -> System.out.println("Cód: " + m.codigo() + " | Marca: " + m.nome()));


        System.out.println("====================================================");
        System.out.print("Digite o nome da marca que você busca: ");
        String escolha = scan.nextLine();

        return marcas.stream()
                .filter(m -> m.nome().toUpperCase().contains(escolha.toUpperCase()))
                .findFirst()
                .map(MarcaDados::codigo)
                .orElse(null);
    }


    public Integer exibirEscolhaModelo(TipoVeiculo tipoVeiculo, Integer codigoMarca){
        List<ModeloDados> modelos = institutoFIPE.obterModelos(tipoVeiculo,codigoMarca);

        modelos.stream()
                .sorted(Comparator.comparing(ModeloDados::codigo))
                .forEach(m -> System.out.println("Cód: " + m.codigo() + " | Modelo: " + m.nome()));

        System.out.println("====================================================");
        System.out.print("Digite o número do modelo que você busca: ");

        return Integer.parseInt(scan.nextLine());
    }

    public String exibirAnosPorModelo(TipoVeiculo tipoVeiculo, Integer codigoMarca, Integer codigoModelo){
        List<AnoDados> anos = institutoFIPE.obterAnosPeloModelo(tipoVeiculo,codigoMarca,codigoModelo);

        anos.stream()
                .sorted(Comparator.comparing(AnoDados::codigoAno))
                .forEach(m -> System.out.println("Cód: " + m.codigoAno() + " | Ano e Combustível: " + m.nomeAno()));

        System.out.println("====================================================");
        System.out.print("Digite o número do código de ano que você deseja ver os modelos: ");

        return scan.nextLine();
    }

    public String exibirAnosPelaMarca(TipoVeiculo tipoVeiculo, Integer codigoMarca){
        List<AnoDados> anos = institutoFIPE.obterAnosPelaMarca(tipoVeiculo,codigoMarca);

        anos.stream()
                .sorted(Comparator.comparing(AnoDados::codigoAno))
                .forEach(m -> System.out.println("Cód: " + m.codigoAno() + " | Ano e Combustível: " + m.nomeAno()));

        System.out.println("====================================================");
        System.out.print("Digite o número do código de ano que você deseja ver os modelos: ");

        return scan.nextLine();
    }
    public Integer exibirModelosPelaMarcaEAno(TipoVeiculo tipoVeiculo, Integer codigoMarca, String codigoAno) {
        List<ModeloDados> modelos = institutoFIPE.obterModelosPelaMarcaEAno(tipoVeiculo,codigoMarca,codigoAno);

        modelos.stream()
                .sorted(Comparator.comparing(ModeloDados::codigo))
                .forEach(m -> System.out.println("Cód: " + m.codigo() + " | Modelo: " + m.nome()));

        System.out.println("====================================================");
        System.out.print("Digite o número do código do modelo que você deseja ver a FIPE: ");
        return Integer.parseInt(scan.nextLine());
    }


    public Veiculo exibirFipeDeUmVeiculo(TipoVeiculo tipoVeiculo, Integer codigoMarca, Integer codigoModelo, String codigoAno){
        return institutoFIPE.obterFipeDeUmVeiculo(tipoVeiculo,codigoMarca,codigoModelo,codigoAno);
    }



    public void rodarPrograma(){
        boolean continuarRodando = true;

        TipoVeiculo tipoVeiculo;
        Integer numeroMarca;
        Integer numeroModelo;
        String numeroAno;

        while (continuarRodando) {
            exibirMenu();
            int opcao = scan.nextInt();
            scan.nextLine();
            switch (opcao) {
                case 1:
                    tipoVeiculo = exibirEscolhaTipoVeiculo();
                    numeroMarca = exibirEscolhaMarca(tipoVeiculo);
                    numeroModelo = exibirEscolhaModelo(tipoVeiculo, numeroMarca);
                    numeroAno = exibirAnosPorModelo(tipoVeiculo,numeroMarca,numeroModelo);
                    System.out.println(exibirFipeDeUmVeiculo(tipoVeiculo,numeroMarca,numeroModelo,numeroAno));
                    break;
                case 2:
                    tipoVeiculo = exibirEscolhaTipoVeiculo();
                    numeroMarca = exibirEscolhaMarca(tipoVeiculo);
                    numeroModelo = exibirEscolhaModelo(tipoVeiculo, numeroMarca);
                    numeroAno = exibirAnosPorModelo(tipoVeiculo,numeroMarca,numeroModelo);
                    Veiculo veiculo = exibirFipeDeUmVeiculo(tipoVeiculo,numeroMarca,numeroModelo,numeroAno);

                    System.out.println("====================================================");
                    System.out.println("Modelo: " + veiculo.getModelo() + "\n"
                            + "Marca: " + veiculo.getMarca() + "\n"
                            + "Ano: " + veiculo.getAno() + "\n"
                            + "Preço atual da Fipe: " + veiculo.getPreco() + "\n"
                            + "Histórico de Preços:" + veiculo.getHistoricoPreco());
                    System.out.println("====================================================");

                    break;
                case 3:
                        //TODO
                    break;
                case 4:
                    continuarRodando = false;
                    break;
                default:
                    throw new IllegalStateException("Opção invalida");
            }
        }
    }



}
