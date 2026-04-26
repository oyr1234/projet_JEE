package com.example.biblio.model;

import jakarta.persistence.*;

@Entity
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String auteur;
    private String isbn;
    private int quantite;

    public Livre() {}

    public Livre(String titre, String auteur, String isbn, int quantite) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.quantite = quantite;
    }

    // getters & setters
    public Long getId() { return id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
}