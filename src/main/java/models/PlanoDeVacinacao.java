package models;

import models.enums.Comorbidades;
import models.enums.Profissao;
import models.observable.Observable;
import models.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class PlanoDeVacinacao implements Observable {

    private Integer idadeMinimaParaVacinacao;
    private List<Observer> cidadaos;
    private final List<Profissao> profissoesPermitidas;
    private final List<Comorbidades> comorbidadesPermitidas;

    public PlanoDeVacinacao() {
        profissoesPermitidas = new ArrayList<>();
        comorbidadesPermitidas = new ArrayList<>();
        cidadaos = new ArrayList<>();
    }

    public Integer getIdadeMinimaParaVacinacao() {
        return idadeMinimaParaVacinacao;
    }

    public void setIdadeMinimaParaVacinacao(Integer idadeMinimaParaVacinacao) {
        this.idadeMinimaParaVacinacao = idadeMinimaParaVacinacao;
        atualizouPlanoDeVacinacao();
    }

    public List<Profissao> getProfissoesPermitidas() {
        return profissoesPermitidas;
    }

    public List<Comorbidades> getComorbidadesPermitidas() {
        return comorbidadesPermitidas;
    }

    public void addComorbidadePermitida(Comorbidades comorbidade) {
        comorbidadesPermitidas.add(comorbidade);
        atualizouPlanoDeVacinacao();
    }

    public void addProfissaoPermitida(Profissao profissao) {
        profissoesPermitidas.add(profissao);
        atualizouPlanoDeVacinacao();
    }

    public void addObserver(Observer observer) {
        cidadaos.add(observer);
    }

    public void removeObserver(Observer observer) {
        cidadaos.remove(observer);
    }

    @Override
    public void atualizouPlanoDeVacinacao() {
        notificaCidadaos();
    }

    @Override
    public void notificaCidadaos() {
        cidadaos.forEach(cidadao -> cidadao.update(this));
    }


}
