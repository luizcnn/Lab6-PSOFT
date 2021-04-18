package models;

import models.states.StatusDeVacinacao;
import models.states.implementations.NaoHabilitado;

import java.time.LocalDate;
import java.util.Set;

public class Cidadao {

    private final String nome;
    private Integer idade;
    private final String cpf;
    private String endereco;
    private String cartaoSUS;
    private String profissao;
    private Set<String> comorbidades;
    private StatusDeVacinacao statusDeVacinacao;
    private LocalDate dataDeVacinacao;
    private PlanoDeVacinacao planoDeVacinacao;

    public Cidadao(String nome, Integer idade, String cpf, String endereco, String cartaoSUS, String profissao,
                   Set<String> comorbidades) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.endereco = endereco;
        this.cartaoSUS = cartaoSUS;
        this.profissao = profissao;
        this.comorbidades = comorbidades;
        this.statusDeVacinacao = new NaoHabilitado(this);
    }

    public void changeStatus(StatusDeVacinacao statusDeVacinacao) {
        this.statusDeVacinacao = statusDeVacinacao;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCartaoSUS() {
        return cartaoSUS;
    }


    public String getProfissao() {
        return profissao;
    }

    public Set<String> getComorbidades() {
        return comorbidades;
    }

    public StatusDeVacinacao getStatusDeVacinacao() {
        return statusDeVacinacao;
    }

    public LocalDate getDataDeVacinacao() {
        return dataDeVacinacao;
    }

    public void setDataDeVacinacao(LocalDate dataDeVacinacao) {
        this.dataDeVacinacao = dataDeVacinacao;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCartaoSUS(String cartaoSUS) {
        this.cartaoSUS = cartaoSUS;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public void setComorbidades(Set<String> comorbidades) {
        this.comorbidades = comorbidades;
    }

    public void addComorbidade(String comorbidade) {
        this.comorbidades.add(comorbidade);
    }

    public PlanoDeVacinacao getPlanoDeVacinacao() {
        return planoDeVacinacao;
    }

    public void setPlanoDeVacinacao(PlanoDeVacinacao planoDeVacinacao) {
        this.planoDeVacinacao = planoDeVacinacao;
    }
}
