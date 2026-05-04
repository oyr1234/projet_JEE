package com.example.biblio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livres")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String auteur;
    private String isbn;
    private int annee;
    private int quantite;
    private boolean disponible = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    public Livre() {}

    public Livre(String titre, String auteur, String isbn,
                 int annee, int quantite, Category category) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.annee = annee;
        this.quantite = quantite;
        this.disponible = quantite > 0;
        this.category = category;
    }

    public boolean verifierDisponibilite() {
        return disponible && quantite > 0;
    }

    public Long getId() { return id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getAnnee() { return annee; }
    public void setAnnee(int annee) { this.annee = annee; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
        this.disponible = quantite > 0;
    }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}