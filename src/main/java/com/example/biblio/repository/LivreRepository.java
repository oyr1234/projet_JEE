package com.example.biblio.repository;

import com.example.biblio.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivreRepository extends JpaRepository<Livre, Long> {

    List<Livre> findByTitreContainingIgnoreCase(String titre);

    List<Livre> findByAuteurContainingIgnoreCase(String auteur);

    List<Livre> findByIsbn(String isbn);

    List<Livre> findByDisponible(boolean disponible);

    List<Livre> findByAnnee(int annee);

    List<Livre> findByCategoryNom(String nom);

    List<Livre> findByQuantiteLessThan(int quantite);
}