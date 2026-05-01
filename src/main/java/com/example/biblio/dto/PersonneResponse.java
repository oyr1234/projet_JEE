package com.example.biblio.dto;

import java.time.LocalDate;

public class PersonneResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String role;
    private boolean enabled;
    private LocalDate dateInscription;
    private String matricule;
    private Double salaire;

    public PersonneResponse(Long id, String nom, String prenom, String email,
                            String telephone, String role, boolean enabled,
                            LocalDate dateInscription,
                            String matricule, Double salaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.role = role;
        this.enabled = enabled;
        this.dateInscription = dateInscription;
        this.matricule = matricule;
        this.salaire = salaire;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public String getRole() { return role; }
    public boolean isEnabled() { return enabled; }
    public LocalDate getDateInscription() { return dateInscription; }
    public String getMatricule() { return matricule; }
    public Double getSalaire() { return salaire; }
}