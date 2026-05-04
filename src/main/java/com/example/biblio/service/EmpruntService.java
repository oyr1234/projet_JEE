package com.example.biblio.service;

import com.example.biblio.model.Emprunt;
import com.example.biblio.model.Livre;
import com.example.biblio.model.User;
import com.example.biblio.repository.EmpruntRepository;
import com.example.biblio.repository.LivreRepository;
import com.example.biblio.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpruntService {

    private final EmpruntRepository empruntRepo;
    private final LivreRepository livreRepo;
    private final UserRepository userRepo;

    public EmpruntService(
            EmpruntRepository empruntRepo,
            LivreRepository livreRepo,
            UserRepository userRepo
    ) {
        this.empruntRepo = empruntRepo;
        this.livreRepo = livreRepo;
        this.userRepo = userRepo;
    }

    // USER requests borrow
    public Emprunt create(Long livreId) {
        Livre livre = livreRepo.findById(livreId)
                .orElseThrow(() -> new RuntimeException("Livre introuvable"));

        // get current logged-in user from security context
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User introuvable"));

        Emprunt emprunt = new Emprunt(LocalDate.now(), livre, user);
        return empruntRepo.save(emprunt);
    }

    public Emprunt valider(Long id) {
        Emprunt emprunt = empruntRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprunt introuvable"));

        emprunt.setValide(true);
        return empruntRepo.save(emprunt);
    }

    public Emprunt retour(Long id) {
        Emprunt emprunt = empruntRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprunt introuvable"));

        emprunt.setRetourne(true);
        emprunt.setDateRetour(LocalDate.now());

        if (LocalDate.now().isAfter(emprunt.getDateLimiteRetour())) {
            emprunt.setEnRetard(true);
        }

        return empruntRepo.save(emprunt);
    }

    public List<Emprunt> getAll() {
        return empruntRepo.findAll();
    }

}