import models.Cidadao;
import models.PlanoDeVacinacao;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static java.util.Objects.nonNull;

public class VacinacaoCovid {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PlanoDeVacinacao planoDeVacinacao = new PlanoDeVacinacao();

        while(true) {
            Integer opcao = menuDeVacinacao(sc);

            if(opcao == 6) {
               System.out.println("Encerrando Sistema de Vacinaçao.");
               break;
            }

            switch (opcao) {
                case 1:
                    cadastroDeCidadaos(sc, planoDeVacinacao);
                    break;
                case 2:
                    exibeStatusDeVacinacaoDeCidadao(sc, planoDeVacinacao);
                    break;
                case 3:
                    atualizarStatusDeVacinacaoDeCidadao(sc, planoDeVacinacao);
                    break;
                case 4:
                    habilitarGrupoPrioritario(sc, planoDeVacinacao);
                    break;
                case 5:
                    aplicarDoseEmCidadao(sc, planoDeVacinacao);
                    break;
                default:
                    throw new IllegalArgumentException("Opçao Invalida. Encerrando a aplicaçao");
            }
        }

    }

    private static void atualizarStatusDeVacinacaoDeCidadao(Scanner sc, PlanoDeVacinacao planoDeVacinacao) {
        System.out.print("CPF: ");
        String cpf = sc.next().trim();

        Cidadao cidadao = planoDeVacinacao.getCidadao(cpf);

        if(nonNull(cidadao)) {
            cidadao.getStatusDeVacinacao().update();
        } else {
            System.out.println("O CPF informado não consta no cadastro.");
        }
    }

    public static Integer menuDeVacinacao(Scanner sc) {
        System.out.println("####### Menu Vacinação - Covid19 #######");
        System.out.println("(1) - Cadastrar Cidadão");
        System.out.println("(2) - Exibe Status de Vacinação");
        System.out.println("(3) - Atualizar Status de Vacinação");
        System.out.println("(4) - Habilitar Grupo Prioritário");
        System.out.println("(5) - Aplicar Dose");
        System.out.println("(6) - Sair");
        System.out.print("Escolha uma opçao: ");

        return sc.nextInt();
    }

    private static void cadastroDeCidadaos(Scanner sc, PlanoDeVacinacao planoDeVacinacao) {
        System.out.print("Nome: ");
        String nome = sc.next();
        sc.nextLine();

        System.out.print("Idade: ");
        Integer idade = sc.nextInt();

        System.out.print("CPF: ");
        String cpf = sc.next().trim();

        System.out.print("Endereço: ");
        String endereco = sc.next();
        sc.nextLine();

        System.out.print("Cartão do SUS: ");
        String sus = sc.next().trim();
        sc.nextLine();

        System.out.print("Profissão: ");
        String profissao = sc.next();
        sc.nextLine();

        System.out.print("Comorbidades (separe-as por vírgulas): ");
        Set<String> comorbidades = converteParaSet(sc.nextLine());

        Cidadao cidadao = new Cidadao(nome, idade, cpf, endereco, sus, profissao, comorbidades);
        planoDeVacinacao.addCidadao(cidadao);

        System.out.println("Cadastro realizado com sucesso! \n");
    }

    private static Set<String> converteParaSet(String entrada) {
        Set<String> set = new HashSet<>();
        String[] splited = entrada.split(", ");

        Collections.addAll(set, splited);

        return set;
    }

    private static void exibeStatusDeVacinacaoDeCidadao(Scanner sc, PlanoDeVacinacao planoDeVacinacao) {
        System.out.print("CPF: ");
        String cpf = sc.next().trim();

        Cidadao cidadao = planoDeVacinacao.getCidadao(cpf);

        if(nonNull(cidadao)) {
            System.out.println(cidadao.getStatusDeVacinacao());
        } else {
            System.out.println("O CPF informado não consta no cadastro.");
        }
    }

    private static void aplicarDoseEmCidadao(Scanner sc, PlanoDeVacinacao planoDeVacinacao) {
        System.out.print("CPF: ");
        String cpf = sc.next().trim();

        Cidadao cidadao = planoDeVacinacao.getCidadao(cpf);

        if(nonNull(cidadao)) {
            cidadao.getStatusDeVacinacao().aplicarDose();
        } else {
            System.out.println("O CPF informado não consta no cadastro.");
        }
    }

    private static void habilitarGrupoPrioritario(Scanner sc, PlanoDeVacinacao planoDeVacinacao) {
        Integer opcao = menuGrupoPrioritario(sc);

        switch (opcao) {
            case 1:
                habilitaGrupoPorIdade(sc, planoDeVacinacao);
                break;
            case 2:
                habilitaGrupoPorProfissao(sc, planoDeVacinacao);
                break;
            case 3:
                habilitaGrupoPorComorbidade(sc, planoDeVacinacao);
                break;
            default:
                throw new IllegalArgumentException("Opçao Invalida. Encerrando a aplicaçao");
        }

        System.out.println("Priorização realizada com sucesso.");
    }

    private static Integer menuGrupoPrioritario(Scanner sc) {
        System.out.println("Qual critério você deseja definir ?");
        System.out.println("(1) - Habilitar grupo prioritário a partir de idade mínima");
        System.out.println("(2) - Habilitar grupo prioritário por profissão");
        System.out.println("(3) - Habilitar grupo prioritário por comorbidade");
        System.out.print("Escolha uma opçao: ");

        return sc.nextInt();
    }

    private static void habilitaGrupoPorIdade(Scanner sc, PlanoDeVacinacao planoDeVacinacao) {
        System.out.println("A partir de qual idade poderá ocorrer a aplicação da primeira dose?");
        System.out.print("Informe o valor: ");
        Integer idadeMinima = sc.nextInt();

        planoDeVacinacao.setIdadeMinimaParaVacinacao(idadeMinima);
    }

    private static void habilitaGrupoPorProfissao(Scanner sc, PlanoDeVacinacao planoDeVacinacao) {
        while(true) {
            System.out.println("Qual profissão deseja adicionar ao plano de vacinação?");
            System.out.print("Informe aqui: ");
            String profissao = sc.next();

            planoDeVacinacao.addProfissaoPermitida(profissao);

            System.out.println("Deseja adicionar mais uma profissão ? \n [S]im ou [N]ão");
            System.out.print("Informe aqui: ");
            String opcao = sc.next();

            if(opcao.equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    private static void habilitaGrupoPorComorbidade(Scanner sc, PlanoDeVacinacao planoDeVacinacao) {
        while(true) {
            System.out.println("Qual comorbidade deseja adicionar ao plano de vacinação?");
            System.out.print("Informe aqui: ");
            String comorbidade = sc.next();

            planoDeVacinacao.addComorbidadePermitida(comorbidade);

            System.out.println("Deseja adicionar mais uma comorbidade ? \n [S]im ou [N]ão");
            System.out.print("Informe aqui: ");
            String opcao = sc.next();

            if(opcao.equalsIgnoreCase("n")) {
                break;
            }
        }
    }

}
