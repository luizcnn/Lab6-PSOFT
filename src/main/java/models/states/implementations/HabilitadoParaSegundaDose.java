package models.states.implementations;

import models.Cidadao;
import models.enums.Comorbidades;
import models.enums.Profissao;
import models.states.StatusDeVacinacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HabilitadoParaSegundaDose implements StatusDeVacinacao {
    private final Cidadao cidadao;

    public HabilitadoParaSegundaDose(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    @Override
    public void habilitaParaPrimeiraDose(Integer idadePermitida, List<Profissao> profissoesPermitidas, List<Comorbidades> comorbidadesPermitidas) {
        System.out.println("O cidadão já iniciou o processo de vacinação");
    }

    @Override
    public void registraData() {
        if(cidadao.getStatusDeVacinacao() instanceof VacinacaoFinalizada) {
            LocalDate registro = LocalDate.now();
            System.out.println("Data de Vacinação (2a dose): " + registro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            cidadao.setDataDeVacinacao(registro);
        } else {
            System.out.println("O cidadão deve tomar a segunda dose antes de registrar a data de vacinação.");
        }
    }

    @Override
    public void aplicarDose() {
        System.out.println("Aplicando segunda dose e registrando a data de vacinação...");
        cidadao.changeStatus(new VacinacaoFinalizada(cidadao));
        registraData();
    }

    @Override
    public void verificaIntervaloParaSegundaDose() {
        System.out.println("O cidadão já está habilitado para tomar a segunda dose.");
    }

    @Override
    public String toString() {
        return String.format(
                "O Cidadão %s de CPF %s está habilitado para tomar a segunda dose.",
                cidadao.getNome(),
                cidadao.getCpf()
        );
    }
}
