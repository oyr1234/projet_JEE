package com.example.biblio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends Personne {

    private boolean enabled = true;

    public Admin() {
        super();
    }

    public Admin(String nom, String prenom, String email,
                 String telephone, String motDePasse) {
        super(nom, prenom, email, telephone, motDePasse);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}