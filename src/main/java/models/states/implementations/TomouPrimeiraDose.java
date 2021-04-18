package models.states.implementations;

import models.Cidadao;
import models.states.StatusDeVacinacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TomouPrimeiraDose implements StatusDeVacinacao {

    private final Cidadao cidadao;

    public TomouPrimeiraDose(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    @Override
    public void update() {
        if(verificaPrazoParaSegundaDose()) {
            System.out.printf("O cidadão %s de CPF %s está habilitado à tomar a segunda dose.",
                    cidadao.getNome(), cidadao.getCpf()
            );
            cidadao.changeStatus(new HabilitadoParaSegundaDose(cidadao));
        } else {
            deveAguardarPrazoDeDias();
        }
    }

    @Override
    public void aplicarDose() {
        if(verificaPrazoParaSegundaDose()) {
            update();
        } else {
            deveAguardarPrazoDeDias();
        }
    }

    public boolean verificaPrazoParaSegundaDose() {
        return LocalDate.now().minusDays(20).compareTo(cidadao.getDataDeVacinacao()) >= 0;
    }

    public void deveAguardarPrazoDeDias() {
        System.out.println("O cidadão ainda deve aguardar o intervalo de dias mínimo " +
                "para a segunda dose. Está previsto para: " +
                cidadao.getDataDeVacinacao().plusDays(20).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
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
