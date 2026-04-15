package it.esempio.service;

import it.esempio.entity.Libro;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless // Gestisce transazioni automatiche -> operazioni che si concludono in una singola chiamata, senza stato
public class LibroService {                         // Contiene la logica di business (EJB)
    @PersistenceContext(unitName = "LibreriaUnit")    // Indica quale database usare (sta dentro persistence.xml) -> LibreriaPU
    private EntityManager em;                       // L'oggetto che comunica con il DB -> Inversion of Control (IoC)
                                                    // Dichiaro che mi serve l'oggetto, il server lo crea

    public void salva(String titolo) {
        em.persist(new Libro(titolo));              // Crea un nuovo oggetto Libro e inseriscilo come entry nel DB
    }

    // Ritorna una lista dei libri (List<Libro>)
    public List<Libro> tutti() {                    // Tramite il JPQL viene interrogata la classe Libro
        return em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
    }
}