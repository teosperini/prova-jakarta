package it.esempio.entity;

import jakarta.persistence.*;

// Mapping oggetto-relazione (Object-Relational Mapping, ORM)
@Entity     // Tramite questo comando sto facendo in modo che questo oggetto venga trasformato in una tabella
@Table(name = "LIBRO")
public class Libro {    // API per far corrispondere Java -> database (JPA)
    @Id                     // Serve a far capire al framework JPA che la prossima variabile è la primary key
    @GeneratedValue(strategy = GenerationType.AUTO)         // Serve ad abilitare l'autoincrement dell'id nel DB
    private Long id;        // Campo id nel DB
    private String titolo;  // Campo titolo nel DB

    public Libro() {}   // Serve a creare una entry nella tabella Libro nel DB

    // Overloading del costruttore
    public Libro(String titolo) { this.titolo = titolo; }

    // Getter e setter
    public Long getId() { return id; }
    public String getTitoloski() { return titolo; }
    public void setTitoloski(String titolo) { this.titolo = titolo; }
}