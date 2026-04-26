package com.example.biblio.model;

import jakarta.persistence.*;

@Entity
public class Bibliothecaire extends Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricule;
    private double salaire;

    public Bibliothecaire() {}

    public Bibliothecaire(String nom, String prenom, String email, String telephone,
                          String matricule, double salaire) {
        super(nom, prenom, email, telephone);
        this.matricule = matricule;
        this.salaire = salaire;
    }

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }
}