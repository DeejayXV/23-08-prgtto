package catalogo.biblioteca;

import javax.persistence.Entity;

@Entity
public class Rivista extends ElementoCatalogo {

    public enum Periodicita {
        SETTIMANALE,
        MENSILE,
        SEMESTRALE
    }

    private Periodicita periodicita;

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }
}
