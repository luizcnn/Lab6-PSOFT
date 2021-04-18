package models.states.implementations;

import models.Cidadao;
import models.states.StatusDeVacinacao;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class NaoHabilitado implements StatusDeVacinacao {

    private final Cidadao cidadao;

    public NaoHabilitado(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    @Override
    public void update() {
        if(habilitaParaPrimeiraDose()) {
            cidadao.changeStatus(new HabilitadoParaPrimeiraDose(cidadao));
        }
    }

    @Override
    public void aplicarDose() {
        System.out.printf(toString());
    }

    public boolean habilitaParaPrimeiraDose() {

        List<String> resultados = Arrays.asList(verificaIdade(), verificaProfissao(), verificaComorbidades());

        if (resultados.stream().allMatch(Predicate.isEqual(null))) {
            System.out.println(String.format(
                    "O cidadão %s de CPF: %s no momento não se enquadra nos critérios do plano de vacinação.",
                    cidadao.getNome(),
                    cidadao.getCpf()
            ));
            return false;
        } else {
            resultados.stream().filter(Objects::nonNull).findFirst().ifPresent(System.out::println);
            return true;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "O Cidadão %s de CPF: %s não está habilitado para vacinação.",
                cidadao.getNome(),
                cidadao.getCpf()
        );
    }

    private String verificaIdade() {
        if (nonNull(cidadao.getPlanoDeVacinacao().getIdadeMinimaParaVacinacao()) &&
                cidadao.getIdade() > cidadao.getPlanoDeVacinacao().getIdadeMinimaParaVacinacao()) {
            return String.format(
                    "Pelo critério de idade, o cidadão %s de CPF: %s está habilitado para tomar a primeira dose.%n",
                    cidadao.getNome(),
                    cidadao.getCpf());
        }
        return null;
    }

    private String verificaProfissao() {
        if (cidadao.getPlanoDeVacinacao().getProfissoesPermitidas().contains(cidadao.getProfissao())) {
            return String.format(
                    "Pelo critério de profissão, o cidadão %s de CPF: %s está habilitado para tomar a primeira dose.%n",
                    cidadao.getNome(),
                    cidadao.getCpf());
        }
        return null;
    }


    private String verificaComorbidades() {

        List<String> c = cidadao.getComorbidades().stream()
                .filter(comorbidade -> cidadao.getPlanoDeVacinacao().getComorbidadesPermitidas().contains(comorbidade))
                .collect(Collectors.toList());

        if(!c.isEmpty()) {
            return String.format(
                    "Pelo critério de comorbidades, o cidadão %s de CPF: %s está habilitado para tomar a primeira dose.%n",
                    cidadao.getNome(),
                    cidadao.getCpf());
        }
        return null;
    }
}
