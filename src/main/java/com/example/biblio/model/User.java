package com.example.biblio.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@PrimaryKeyJoinColumn(name = "id")
public class User extends Personne {

    private boolean enabled = true;

    private LocalDate dateInscription = LocalDate.now();

    public User() {
        super();
    }

    public User(String nom, String prenom, String email,
                String telephone, String motDePasse) {
        super(nom, prenom, email, telephone, motDePasse);
        this.dateInscription = LocalDate.now();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }
}