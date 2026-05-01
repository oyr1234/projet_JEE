package com.example.biblio.dto;

public class LoginResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String role;
    private String token; // ← added

    public LoginResponse(Long id, String nom, String prenom,
                         String email, String role, String token) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        this.token = token;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getToken() { return token; }
}