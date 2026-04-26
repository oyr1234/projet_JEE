package com.example.biblio.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    private boolean valide;

    // relation avec livre
    @ManyToOne
    private Livre livre;

    public Emprunt() {}

    public Emprunt(LocalDate dateEmprunt, Livre livre) {
        this.dateEmprunt = dateEmprunt;
        this.livre = livre;
        this.valide = false;
    }

    // getters & setters
    public Long getId() { return id; }

    public LocalDate getDateEmprunt() { return dateEmprunt; }
    public void setDateEmprunt(LocalDate dateEmprunt) { this.dateEmprunt = dateEmprunt; }

    public LocalDate getDateRetour() { return dateRetour; }
    public void setDateRetour(LocalDate dateRetour) { this.dateRetour = dateRetour; }

    public boolean isValide() { return valide; }
    public void setValide(boolean valide) { this.valide = valide; }

    public Livre getLivre() { return livre; }
    public void setLivre(Livre livre) { this.livre = livre; }
}