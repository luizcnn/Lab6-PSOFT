package models.observable;

import models.observer.Observer;

public interface Observable {

    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void atualizouPlanoDeVacinacao();
    void notificaCidadaos();

}
