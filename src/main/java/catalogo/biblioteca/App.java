package catalogo.biblioteca;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Creazione di un utente
        Utente utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setDataNascita(LocalDate.of(1985, 5, 15));
        utente.setNumeroTessera("12345");

        em.persist(utente);

        // Creazione di un libro
        Libro libro = new Libro();
        libro.setCodiceIsbn("978-3-16-148410-0");
        libro.setTitolo("Il Signore degli Anelli");
        libro.setAnnoPubblicazione(1954);
        libro.setNumeroPagine(1216);
        libro.setAutore("J.R.R. Tolkien");
        libro.setGenere("Fantasy");

        em.persist(libro);

        // Creazione di un prestito
        Prestito prestito = new Prestito(utente, libro, LocalDate.now());
        em.persist(prestito);

        em.getTransaction().commit();

        // Recupero e stampa dei dettagli del prestito
        Prestito prestitoPersistito = em.find(Prestito.class, prestito.getId());
        System.out.println("Prestito registrato: " + prestitoPersistito.getElementoPrestato().getTitolo() +
                ", Utente: " + prestitoPersistito.getUtente().getNome() + " " +
                prestitoPersistito.getUtente().getCognome() +
                ", Data restituzione prevista: " + prestitoPersistito.getDataRestituzionePrevista());

        em.close();
        emf.close();
    }
}
