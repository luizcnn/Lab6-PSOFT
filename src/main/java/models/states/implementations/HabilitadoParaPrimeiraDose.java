package models.states.implementations;

import models.Cidadao;
import models.states.StatusDeVacinacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HabilitadoParaPrimeiraDose implements StatusDeVacinacao {

    private final Cidadao cidadao;

    public HabilitadoParaPrimeiraDose(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    @Override
    public void update() {
        aplicarDose();
    }

    public void registraData() {
        if(cidadao.getStatusDeVacinacao() instanceof TomouPrimeiraDose) {
            LocalDate registro = LocalDate.now();
            System.out.printf("Data de Vacinação (1a dose) de %s (CPF: %s): %s\n",
                    cidadao.getNome(),
                    cidadao.getCpf(),
                    registro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            );
            cidadao.setDataDeVacinacao(registro);
        } else {
            System.out.println("Você deve tomar a dose antes de registrar a data de vacinação.");
        }
    }

    public void aplicarDose() {
        cidadao.changeStatus(new TomouPrimeiraDose(cidadao));
        registraData();
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
