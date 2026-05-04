package com.example.biblio.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("BIBLIOTHECAIRE")
public class Bibliothecaire extends User {

    private String matricule;
    private double salaire;

    public Bibliothecaire() {
        setRole(Role.BIBLIOTHECAIRE);  // ✅ auto set role
    }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public double getSalaire() { return salaire; }
    public void setSalaire(double salaire) { this.salaire = salaire; }
}