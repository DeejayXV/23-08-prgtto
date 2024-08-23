package catalogo.biblioteca;

import javax.persistence.Entity;

@Entity
public class Rivista extends ElementoCatalogo {

    private String periodicita;

    // Getters e Setters
    public String getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(String periodicita) {
        this.periodicita = periodicita;
    }
}
