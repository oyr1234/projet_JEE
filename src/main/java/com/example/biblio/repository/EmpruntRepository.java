package com.example.biblio.repository;

import com.example.biblio.model.Emprunt;
import com.example.biblio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    List<Emprunt> findByValide(boolean valide);

    List<Emprunt> findByRetourne(boolean retourne);

    List<Emprunt> findByEnRetard(boolean enRetard);

    List<Emprunt> findByUser(User user);  // ← add this
    List<Emprunt> findByUserId(Long userId);
}