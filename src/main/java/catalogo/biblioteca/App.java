package catalogo.biblioteca;

import java.time.LocalDate;
import java.util.List;

public class App {

    public static void main(String[] args) {
        Archivio archivio = new Archivio();

        // Aggiunta di un libro al catalogo
        Libro libro = new Libro();
        libro.setCodiceIsbn("978-3-16-148410-0");
        libro.setTitolo("Il Signore degli Anelli");
        libro.setAnnoPubblicazione(1954);
        libro.setNumeroPagine(1216);
        libro.setAutore("J.R.R. Tolkien");
        libro.setGenere("Fantasy");
        archivio.aggiungiElemento(libro);

        // Aggiunta di una rivista al catalogo
        Rivista rivista = new Rivista();
        rivista.setCodiceIsbn("978-1-23-456789-0");
        rivista.setTitolo("National Geographic");
        rivista.setAnnoPubblicazione(2024);
        rivista.setNumeroPagine(100);
        rivista.setPeriodicita(Rivista.Periodicita.MENSILE);
        archivio.aggiungiElemento(rivista);

        // Creazione di un utente
        Utente utente = new Utente();
        utente.setNome("Aldo");
        utente.setCognome("Baglio");
        utente.setDataNascita(LocalDate.of(1985, 5, 15));
        utente.setNumeroTessera("123456");
        archivio.aggiungiUtente(utente);

        // Creazione di un prestito
        Prestito prestito = new Prestito(utente, libro, LocalDate.now());
        archivio.aggiungiPrestito(prestito);

        // Ricerca per ISBN
        ElementoCatalogo elemento = archivio.ricercaPerIsbn("978-3-16-148410-0");
        System.out.println("Elemento trovato per ISBN: " + elemento.getTitolo());

        // Ricerca per anno di pubblicazione
        List<ElementoCatalogo> elementiAnno = archivio.ricercaPerAnno(1954);
        System.out.println("Elementi trovati per anno 1954: " + elementiAnno.size());

        // Ricerca per autore
        List<Libro> libriAutore = archivio.ricercaPerAutore("J.R.R. Tolkien");
        System.out.println("Libri trovati per autore J.R.R. Tolkien: " + libriAutore.size());

        // Ricerca per titolo o parte di esso
        List<ElementoCatalogo> elementiTitolo = archivio.ricercaPerTitolo("Signore");
        System.out.println("Elementi trovati per titolo contenente 'Signore': " + elementiTitolo.size());

        // Ricerca degli elementi attualmente in prestito per numero tessera
        List<Prestito> prestitiUtente = archivio.ricercaPrestitiPerNumeroTessera("12345");
        System.out.println("Prestiti attivi per utente con tessera 12345: " + prestitiUtente.size());

        // Ricerca di tutti i prestiti scaduti e non ancora restituiti
        List<Prestito> prestitiScaduti = archivio.ricercaPrestitiScadutiNonRestituiti();
        System.out.println("Prestiti scaduti e non ancora restituiti: " + prestitiScaduti.size());

        archivio.close();
    }
}
