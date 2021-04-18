package models.states.implementations;

import models.Cidadao;
import models.states.StatusDeVacinacao;

public class VacinacaoFinalizada implements StatusDeVacinacao {
    private final Cidadao cidadao;

    public VacinacaoFinalizada(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    @Override
    public void update() {
        System.out.println(toString());
    }

    @Override
    public void aplicarDose() {
        System.out.println(toString());
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
