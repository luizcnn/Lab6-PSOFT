import models.Cidadao;
import models.PlanoDeVacinacao;
import models.enums.Comorbidades;
import models.enums.Profissao;

import java.time.LocalDate;

public class VacinacaoCovid {

    public static void definePlanoDeVacinacaoInicial(PlanoDeVacinacao planoDeVacinacao, Integer idadePermitida,
            Profissao profissaoPermitida, Comorbidades comorbidadePermitida) {

        planoDeVacinacao.setIdadeMinimaParaVacinacao(idadePermitida);
        planoDeVacinacao.addProfissaoPermitida(profissaoPermitida);
        planoDeVacinacao.addComorbidadePermitida(comorbidadePermitida);
    }

    public static void main(String[] args) {

        PlanoDeVacinacao planoDeVacinacao = new PlanoDeVacinacao();

        Cidadao cidadao = new Cidadao(
                "Maria Pereira",
                40,
                "10120010109",
                "Rua das Acácias",
                "12345678910",
                Profissao.SAUDE,
                Comorbidades.SEM_COMORBIDADES
        );

        planoDeVacinacao.addObserver(cidadao);

        definePlanoDeVacinacaoInicial(planoDeVacinacao,80, Profissao.SAUDE, Comorbidades.NIVEL_A);

        System.out.println(cidadao.getStatusDeVacinacao().toString());
//        cidadao.getStatusDeVacinacao().registraData();
//        cidadao.getStatusDeVacinacao().habilitaParaPrimeiraDose(
//                planoDeVacinacao.getIdadeMinimaParaVacinacao(),
//                planoDeVacinacao.getProfissoesPermitidas(),
//                planoDeVacinacao.getComorbidadesPermitidas()
//        );

        //A cidadã se habilitará para a primeira dose.

//        System.out.println(cidadao.getStatusDeVacinacao());

        //Aplicando a dose no cidadão:
        cidadao.getStatusDeVacinacao().aplicarDose();

        // Verificando status atual de vacinação.
        System.out.println(cidadao.getStatusDeVacinacao());

        // Verificando se está habilitada para segunda dose.
        cidadao.getStatusDeVacinacao().verificaIntervaloParaSegundaDose();

        // Simulando o cidadão com data suficiente para segunda dose:
        cidadao.setDataDeVacinacao(LocalDate.of(2021, 3, 20));
        cidadao.getStatusDeVacinacao().verificaIntervaloParaSegundaDose();

        // Verificando status atual de vacinação
        System.out.println(cidadao.getStatusDeVacinacao());

        // Tomando segunda dose:
        cidadao.getStatusDeVacinacao().aplicarDose();

        // Verificando status atual de vacinação
        System.out.println(cidadao.getStatusDeVacinacao());

    }
}
