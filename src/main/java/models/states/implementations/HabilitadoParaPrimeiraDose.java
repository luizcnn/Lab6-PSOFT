package models.states.implementations;

import models.Cidadao;
import models.enums.Comorbidades;
import models.enums.Profissao;
import models.states.StatusDeVacinacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HabilitadoParaPrimeiraDose implements StatusDeVacinacao {

    private final Cidadao cidadao;

    public HabilitadoParaPrimeiraDose(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    @Override
    public void habilitaParaPrimeiraDose(Integer idadePermitida, List<Profissao> profissoesPermitidas, List<Comorbidades> comorbidadesPermitidas) {
        System.out.println("O cidadão já está habilitado para tomar a primeira dose.");
    }

    @Override
    public void registraData() {
        if(cidadao.getStatusDeVacinacao() instanceof TomouPrimeiraDose) {
            LocalDate registro = LocalDate.now();
            System.out.println("Data de Vacinação (1a dose): " + registro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            cidadao.setDataDeVacinacao(registro);
        } else {
            System.out.println("Você deve tomar a dose antes de registrar a data de vacinação.");
        }
    }

    @Override
    public void aplicarDose() {
        System.out.println("Aplicando a dose e registrando a data de vacinação...");
        cidadao.changeStatus(new TomouPrimeiraDose(cidadao));
        registraData();
    }

    @Override
    public void verificaIntervaloParaSegundaDose() {
        System.out.println("Você ainda não tomou a primeira dose. Após toma-la, aguarde 20 dias para a próxima dose.");
    }

    @Override
    public String toString() {
        return String.format(
                "O Cidadão %s de CPF %s está habilitado para tomar a primeira dose.",
                cidadao.getNome(),
                cidadao.getCpf()
        );
    }
}
