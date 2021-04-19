package models;

import models.states.implementations.NaoHabilitado;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlanoDeVacinacao {

    private Integer idadeMinimaParaVacinacao;
    private final Map<String, Cidadao> cidadaos;
    private final Set<String> profissoesPermitidas;
    private final Set<String> comorbidadesPermitidas;

    public PlanoDeVacinacao() {
        this.profissoesPermitidas = new HashSet<>();
        this.comorbidadesPermitidas = new HashSet<>();
        this.cidadaos = new HashMap<>();
    }

    public Integer getIdadeMinimaParaVacinacao() {
        return idadeMinimaParaVacinacao;
    }

    public void setIdadeMinimaParaVacinacao(Integer idadeMinimaParaVacinacao) {
        this.idadeMinimaParaVacinacao = idadeMinimaParaVacinacao;
        notificaCidadaosSobreMudancasNosCriterios();
    }

    public Set<String> getProfissoesPermitidas() {
        return profissoesPermitidas;
    }

    public Set<String> getComorbidadesPermitidas() {
        return comorbidadesPermitidas;
    }

    public void addComorbidadePermitida(String comorbidade) {
        comorbidadesPermitidas.add(comorbidade.toLowerCase());
        notificaCidadaosSobreMudancasNosCriterios();
    }

    public void addProfissaoPermitida(String profissao) {
        profissoesPermitidas.add(profissao.toLowerCase());
        notificaCidadaosSobreMudancasNosCriterios();
    }

    public void addCidadao(Cidadao cidadao) {
        cidadao.setPlanoDeVacinacao(this);
        this.cidadaos.put(cidadao.getCpf(), cidadao);
    }

    public Map<String, Cidadao> getCidadaos() {
        return this.cidadaos;
    }

    public Cidadao getCidadao(String cpf) {
        if(!this.cidadaos.containsKey(cpf)) {
            System.out.println("O CPF informado não consta no cadastro.");
            return null;
        } else {
            return this.cidadaos.get(cpf);
        }
    }

    public void notificaCidadaosSobreMudancasNosCriterios() {
        cidadaos.values().forEach(cidadao -> {
            if (cidadao.getStatusDeVacinacao() instanceof NaoHabilitado)
                cidadao.getStatusDeVacinacao().update();
        });
    }
}
