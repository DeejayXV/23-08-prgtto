package catalogo.biblioteca;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Archivio {

    private EntityManagerFactory emf;

    public Archivio() {
        this.emf = Persistence.createEntityManagerFactory("bibliotecaPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Aggiunta di un elemento al catalogo
    public void aggiungiElemento(ElementoCatalogo elemento) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(elemento);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Aggiunta di un utente
    public void aggiungiUtente(Utente utente) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(utente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Aggiunta di un prestito
    public void aggiungiPrestito(Prestito prestito) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(prestito);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Ricerca per ISBN
    public ElementoCatalogo ricercaPerIsbn(String codiceIsbn) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ElementoCatalogo.class, codiceIsbn);
        } finally {
            em.close();
        }
    }

    // Ricerca per anno di pubblicazione
    public List<ElementoCatalogo> ricercaPerAnno(int anno) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ElementoCatalogo> query = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno", ElementoCatalogo.class);
            query.setParameter("anno", anno);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Ricerca per autore
    public List<Libro> ricercaPerAutore(String autore) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class);
            query.setParameter("autore", autore);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Ricerca per titolo o parte di esso
    public List<ElementoCatalogo> ricercaPerTitolo(String titolo) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ElementoCatalogo> query = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.titolo LIKE :titolo", ElementoCatalogo.class);
            query.setParameter("titolo", "%" + titolo + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Ricerca degli elementi attualmente in prestito dato un numero di tessera utente
    public List<Prestito> ricercaPrestitiPerNumeroTessera(String numeroTessera) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Prestito> query = em.createQuery(
                    "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera AND p.dataRestituzioneEffettiva IS NULL",
                    Prestito.class);
            query.setParameter("numeroTessera", numeroTessera);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Ricerca di tutti i prestiti scaduti e non ancora restituiti
    public List<Prestito> ricercaPrestitiScadutiNonRestituiti() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Prestito> query = em.createQuery(
                    "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE AND p.dataRestituzioneEffettiva IS NULL",
                    Prestito.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }
}
