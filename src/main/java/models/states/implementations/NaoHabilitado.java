package models.states.implementations;

import models.Cidadao;
import models.states.StatusDeVacinacao;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return String.format(
                "O Cidadão %s de CPF: %s não está habilitado para vacinação.",
                cidadao.getNome(),
                cidadao.getCpf()
        );
    }

    private boolean habilitaParaPrimeiraDose() {
        return Stream.of(verificaIdade(), verificaProfissao(), verificaComorbidades()).anyMatch(Predicate.isEqual(true));
    }

    private boolean verificaIdade() {
        return nonNull(cidadao.getPlanoDeVacinacao().getIdadeMinimaParaVacinacao()) &&
                cidadao.getIdade() > cidadao.getPlanoDeVacinacao().getIdadeMinimaParaVacinacao();
    }

    private boolean verificaProfissao() {
        return cidadao.getPlanoDeVacinacao().getProfissoesPermitidas().contains(cidadao.getProfissao());
    }

    private boolean verificaComorbidades() {
        return cidadao.getComorbidades().stream().anyMatch(comorbidade ->
                cidadao.getPlanoDeVacinacao().getComorbidadesPermitidas().contains(comorbidade));
    }
}
