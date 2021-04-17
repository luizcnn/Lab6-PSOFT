package models;

import models.enums.Comorbidades;
import models.enums.Profissao;

import java.util.ArrayList;
import java.util.List;

public class PlanoDeVacinacao {

    private Integer idadeMinimaParaVacinacao;
    private final List<Profissao> profissoesPermitidas;
    private final List<Comorbidades> comorbidadesPermitidas;

    public PlanoDeVacinacao() {
        profissoesPermitidas = new ArrayList<>();
        comorbidadesPermitidas = new ArrayList<>();
    }

    public Integer getIdadeMinimaParaVacinacao() {
        return idadeMinimaParaVacinacao;
    }

    public void setIdadeMinimaParaVacinacao(Integer idadeMinimaParaVacinacao) {
        this.idadeMinimaParaVacinacao = idadeMinimaParaVacinacao;
    }

    public List<Profissao> getProfissoesPermitidas() {
        return profissoesPermitidas;
    }

    public List<Comorbidades> getComorbidadesPermitidas() {
        return comorbidadesPermitidas;
    }


    public void addComorbidadePermitida(Comorbidades comorbidade) {
        comorbidadesPermitidas.add(comorbidade);
    }

    public void addProfissaoPermitida(Profissao profissao) {
        profissoesPermitidas.add(profissao);
    }

    public void removeProfissaoPermitida(Profissao profissao) {
        profissoesPermitidas.remove(profissao);
    }

    public void removeComorbidadePermitida(Comorbidades comorbidades) {
        comorbidadesPermitidas.remove(comorbidades);
    }
}
