package com.example.biblio.repository;

import com.example.biblio.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    List<Emprunt> findByValide(boolean valide);

    List<Emprunt> findByRetourne(boolean retourne);

    List<Emprunt> findByEnRetard(boolean enRetard);
}