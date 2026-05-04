package com.example.biblio.dto;

public class PersonneRequest {

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String motDePasse;
    private String matricule;
    private double salaire;
    private String role;

    // =========================
    // CONSTRUCTORS
    // =========================
    public PersonneRequest() {}

    public PersonneRequest(String nom, String prenom, String email, String telephone,
                           String motDePasse, String matricule, double salaire, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        this.matricule = matricule;
        this.salaire = salaire;
        this.role = role;
    }

    // =========================
    // GETTERS & SETTERS
    // =========================
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}