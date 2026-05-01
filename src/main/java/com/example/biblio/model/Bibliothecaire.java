package com.example.biblio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bibliothecaires")
@PrimaryKeyJoinColumn(name = "id")
public class Bibliothecaire extends Personne {

    private String matricule;

    private double salaire;

    private boolean enabled = true;

    public Bibliothecaire() {
        super();
    }

    public Bibliothecaire(String nom, String prenom, String email,
                          String telephone, String matricule,
                          double salaire, String motDePasse) {

        super(nom, prenom, email, telephone, motDePasse);

        this.matricule = matricule;
        this.salaire = salaire;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}