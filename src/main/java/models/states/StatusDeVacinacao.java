package models.states;

import models.enums.Comorbidades;
import models.enums.Profissao;

import java.util.List;

public interface StatusDeVacinacao {

    boolean habilitaParaPrimeiraDose(Integer idadePermitida, List<Profissao> profissoesPermitidas,
                                  List<Comorbidades> comorbidadesPermitidas);
    void registraData();
    void aplicarDose();
    void verificaIntervaloParaSegundaDose();
}
