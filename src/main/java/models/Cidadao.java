package models;

import models.enums.Comorbidades;
import models.enums.Profissao;
import models.observer.Observer;
import models.states.StatusDeVacinacao;
import models.states.implementations.HabilitadoParaPrimeiraDose;
import models.states.implementations.NaoHabilitado;

import java.time.LocalDate;

public class Cidadao implements Observer {

    private final String nome;
    private Integer idade;
    private final String cpf;
    private String endereco;
    private String cartaoSUS;
    private String email;
    private String telefone;
    private Profissao profissao;
    private Comorbidades comorbidades;
    private StatusDeVacinacao statusDeVacinacao;
    private LocalDate dataDeVacinacao;

    public Cidadao(String nome, Integer idade, String cpf, String endereco, String cartaoSUS, Profissao profissao,
                   Comorbidades comorbidades) {
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

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public Comorbidades getComorbidades() {
        return comorbidades;
    }

    public StatusDeVacinacao getStatusDeVacinacao() {
        return statusDeVacinacao;
    }

    public LocalDate getDataDeVacinacao() {
        return dataDeVacinacao;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public void setComorbidades(Comorbidades comorbidades) {
        this.comorbidades = comorbidades;
    }

    @Override
    public void update(PlanoDeVacinacao planoDeVacinacao) {
        this.statusDeVacinacao.habilitaParaPrimeiraDose(
                planoDeVacinacao.getIdadeMinimaParaVacinacao(),
                planoDeVacinacao.getProfissoesPermitidas(),
                planoDeVacinacao.getComorbidadesPermitidas()
        );
    }
}
