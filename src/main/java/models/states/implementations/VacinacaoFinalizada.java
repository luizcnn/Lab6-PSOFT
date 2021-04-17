package models.states.implementations;

import models.Cidadao;
import models.enums.Comorbidades;
import models.enums.Profissao;
import models.states.StatusDeVacinacao;

import java.util.List;

public class VacinacaoFinalizada implements StatusDeVacinacao {
    private final Cidadao cidadao;
    private static final String MSG = "O Cidadão %s de CPF %s já está totalmente vacinado";

    public VacinacaoFinalizada(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    @Override
    public void habilitaParaPrimeiraDose(Integer idadePermitida, List<Profissao> profissoesPermitidas, List<Comorbidades> comorbidadesPermitidas) {
        System.out.println(String.format(MSG, cidadao.getNome(), cidadao.getCpf()));
    }

    @Override
    public void registraData() {
        System.out.println(String.format(MSG, cidadao.getNome(), cidadao.getCpf()));
    }

    @Override
    public void aplicarDose() {
        System.out.println(String.format(MSG, cidadao.getNome(), cidadao.getCpf()));
    }

    @Override
    public void verificaIntervaloParaSegundaDose() {
        System.out.println(String.format(MSG, cidadao.getNome(), cidadao.getCpf()));
    }

    @Override
    public String toString() {
        return String.format(
                "O Cidadão %s de CPF %s já tomou ambas as doses da vacina.",
                cidadao.getNome(),
                cidadao.getCpf()
        );
    }
}
