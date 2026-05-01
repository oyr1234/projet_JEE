package com.example.biblio.service;

import com.example.biblio.model.Emprunt;
import com.example.biblio.model.Livre;
import com.example.biblio.repository.EmpruntRepository;
import com.example.biblio.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpruntService {

    private final EmpruntRepository empruntRepo;
    private final LivreRepository livreRepo;

    public EmpruntService(
            EmpruntRepository empruntRepo,
            LivreRepository livreRepo
    ) {
        this.empruntRepo = empruntRepo;
        this.livreRepo = livreRepo;
    }

    // USER requests borrow
    public Emprunt create(Long livreId) {
        Livre livre = livreRepo.findById(livreId)
                .orElseThrow(() -> new RuntimeException("Livre introuvable"));

        return empruntRepo.save(
                new Emprunt(LocalDate.now(), livre)
        );
    }

    // BIBLIOTHECAIRE validates borrow
    public Emprunt valider(Long id) {
        Emprunt e = empruntRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprunt introuvable"));

        Livre l = e.getLivre();

        if (l.getQuantite() <= 0) {
            throw new RuntimeException("Livre non disponible");
        }

        l.setQuantite(l.getQuantite() - 1);
        livreRepo.save(l);

        e.setValide(true);

        return empruntRepo.save(e);
    }

    // Return book
    public Emprunt retour(Long id) {
        Emprunt e = empruntRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprunt introuvable"));

        Livre l = e.getLivre();

        l.setQuantite(l.getQuantite() + 1);
        livreRepo.save(l);

        e.setDateRetour(LocalDate.now());
        e.setRetourne(true);

        if (LocalDate.now().isAfter(e.getDateLimiteRetour())) {
            e.setEnRetard(true);
        }

        return empruntRepo.save(e);
    }

    public List<Emprunt> getAll() {
        return empruntRepo.findAll();
    }

    public List<Emprunt> pendingRequests() {
        return empruntRepo.findByValide(false);
    }

    public List<Emprunt> returnedBooks() {
        return empruntRepo.findByRetourne(true);
    }

    public List<Emprunt> overdueBooks() {
        return empruntRepo.findByEnRetard(true);
    }
}