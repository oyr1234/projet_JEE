package com.example.biblio.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emprunts")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    // validation by bibliothecaire
    private boolean valide = false;

    // returned or not
    private boolean retourne = false;

    // due date = borrow + 14 days
    private LocalDate dateLimiteRetour;

    // overdue penalty flag
    private boolean enRetard = false;

    @ManyToOne
    private Livre livre;

    public Emprunt() {}

    public Emprunt(LocalDate dateEmprunt, Livre livre) {
        this.dateEmprunt = dateEmprunt;
        this.livre = livre;
        this.valide = false;
        this.retourne = false;
        this.dateLimiteRetour = dateEmprunt.plusDays(14);
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public boolean isRetourne() {
        return retourne;
    }

    public void setRetourne(boolean retourne) {
        this.retourne = retourne;
    }

    public LocalDate getDateLimiteRetour() {
        return dateLimiteRetour;
    }

    public void setDateLimiteRetour(LocalDate dateLimiteRetour) {
        this.dateLimiteRetour = dateLimiteRetour;
    }

    public boolean isEnRetard() {
        return enRetard;
    }

    public void setEnRetard(boolean enRetard) {
        this.enRetard = enRetard;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }
}