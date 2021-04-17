package models.states.implementations;

import models.Cidadao;
import models.enums.Comorbidades;
import models.enums.Profissao;
import models.states.StatusDeVacinacao;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;

public class NaoHabilitado implements StatusDeVacinacao {

    private final Cidadao cidadao;

    public NaoHabilitado(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    @Override
    public boolean habilitaParaPrimeiraDose(Integer idadePermitida, List<Profissao> profissoesPermitidas,
                                            List<Comorbidades> comorbidadesPermitidas) {
        List<String> resultados = Arrays.asList(
                verificaIdade(idadePermitida),
                verificaProfissao(profissoesPermitidas),
                verificaComorbidades(comorbidadesPermitidas)
        );

        for(String resultado : resultados) {
            if(nonNull(resultado)) {
                System.out.println(resultado);
                return true;
            }
        }

        System.out.println(String.format(
                "O cidadão %s de CPF %s no momento não se enquadra nos critérios do plano de vacinação.",
                cidadao.getNome(),
                cidadao.getCpf()
        ));
        return false;

    }

    @Override
    public void registraData() {
        System.out.println("Operação inválida. O registro deve ser feito na aplicação das doses.");
    }

    @Override
    public void aplicarDose() {
        System.out.println("Operação inválida. Ainda no aguardo da habilitação para primeira dose.");
    }

    @Override
    public void verificaIntervaloParaSegundaDose() {
        System.out.println("Operação inválida. Ainda no aguardo da habilitação para primeira dose.");
    }

    @Override
    public String toString() {
        return String.format(
                "O Cidadão %s de CPF %s não está habilitado para vacinação.",
                cidadao.getNome(),
                cidadao.getCpf()
        );
    }

    private String verificaIdade(Integer idadePermitida) {
        if(cidadao.getIdade() > idadePermitida) {
            String result = String.format(
                    "Pelo critério de idade, o cidadão %s de CPF %s está habilitado para tomar a primeira dose.%n",
                    cidadao.getNome(),
                    cidadao.getCpf());
            cidadao.changeStatus(new HabilitadoParaPrimeiraDose(cidadao));
            return result;
        }
        return null;
    }

    private String verificaProfissao(List<Profissao> profissoesPermitidas) {
        if(profissoesPermitidas.contains(cidadao.getProfissao())) {
            String result = String.format(
                    "Pelo critério de profissão, o cidadão %s de CPF %s está habilitado para tomar a primeira dose.%n",
                    cidadao.getNome(),
                    cidadao.getCpf());
            cidadao.changeStatus(new HabilitadoParaPrimeiraDose(cidadao));
            return result;
        }
        return null;
    }


    private String verificaComorbidades(List<Comorbidades> comorbidadesPermitidas) {
        if(comorbidadesPermitidas.contains(cidadao.getComorbidades())) {
            String result = String.format(
                    "Pelo critério de comorbidades, o cidadão %s de CPF %s está habilitado para tomar a primeira dose.%n",
                    cidadao.getNome(),
                    cidadao.getCpf());
            cidadao.changeStatus(new HabilitadoParaPrimeiraDose(cidadao));
            return result;
        }
        return null;
    }
}
