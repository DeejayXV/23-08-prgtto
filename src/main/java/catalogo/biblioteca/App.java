package catalogo.biblioteca;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPU");
        EntityManager em = emf.createEntityManager();

        // Persistenza di un libro
        em.getTransaction().begin();
        Libro libro = new Libro();
        libro.setCodiceIsbn("978-3-16-148410-0");
        libro.setTitolo("Il Signore degli Anelli");
        libro.setAnnoPubblicazione(1954);
        libro.setNumeroPagine(1216);
        libro.setAutore("J.R.R. Tolkien");
        libro.setGenere("Fantasy");

        em.persist(libro);
        em.getTransaction().commit();

        // Persistenza di una rivista
        em.getTransaction().begin();
        Rivista rivista = new Rivista();
        rivista.setCodiceIsbn("978-1-23-456789-0");
        rivista.setTitolo("National Geographic");
        rivista.setAnnoPubblicazione(2024);
        rivista.setNumeroPagine(100);
        rivista.setPeriodicita(Rivista.Periodicita.MENSILE);

        em.persist(rivista);
        em.getTransaction().commit();

        // Recupero e stampa dei dati
        Libro libroPersistito = em.find(Libro.class, "978-3-16-148410-0");
        System.out.println("Libro recuperato: " + libroPersistito.getTitolo() + ", Autore: " + libroPersistito.getAutore() + ", Genere: " + libroPersistito.getGenere());

        Rivista rivistaPersistita = em.find(Rivista.class, "978-1-23-456789-0");
        System.out.println("Rivista recuperata: " + rivistaPersistita.getTitolo() + ", Periodicità: " + rivistaPersistita.getPeriodicita());

        em.close();
        emf.close();
    }
}
