package models.states.implementations;

import models.Cidadao;
import models.enums.Comorbidades;
import models.enums.Profissao;
import models.states.StatusDeVacinacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TomouPrimeiraDose implements StatusDeVacinacao {
    private final Cidadao cidadao;

    public TomouPrimeiraDose(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    @Override
    public boolean habilitaParaPrimeiraDose(Integer idadePermitida, List<Profissao> profissoesPermitidas, List<Comorbidades> comorbidadesPermitidas) {
        System.out.println("Verificação desnecessária. O cidadão já tomou a primeira dose.");
        return false;
    }

    @Override
    public void registraData() {
        System.out.println("Data de Vacinação já registrada.");
    }

    @Override
    public void aplicarDose() {
        System.out.println("O cidadão já tomou a primeira dose.");
    }

    @Override
    public void verificaIntervaloParaSegundaDose() {
        System.out.println("Verificando se intervalo da primeira dose já foi cumprido...");
        if(LocalDate.now().minusDays(20).compareTo(cidadao.getDataDeVacinacao()) >= 0) {
            System.out.println("O cidadão está habilitado à tomar a segunda dose.");
            cidadao.changeStatus(new HabilitadoParaSegundaDose(cidadao));
        } else {
            System.out.println("O cidadão ainda deve aguardar o intervalo de dias mínimo " +
                    "para a segunda dose. Está previsto para: " +
                    cidadao.getDataDeVacinacao().plusDays(20).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
    }

    @Override
    public String toString() {
        return String.format(
                "O Cidadão %s de CPF %s tomou a primeira dose em: %s.",
                cidadao.getNome(),
                cidadao.getCpf(),
                cidadao.getDataDeVacinacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        );
    }
}
