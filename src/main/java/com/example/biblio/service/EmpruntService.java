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

    public EmpruntService(EmpruntRepository empruntRepo, LivreRepository livreRepo) {
        this.empruntRepo = empruntRepo;
        this.livreRepo = livreRepo;
    }

    // créer demande emprunt
    public Emprunt create(Long livreId) {
        Livre livre = livreRepo.findById(livreId).orElseThrow();

        return empruntRepo.save(new Emprunt(LocalDate.now(), livre));
    }

    // validation (bibliothécaire)
    public Emprunt valider(Long id) {
        Emprunt e = empruntRepo.findById(id).orElseThrow();

        Livre l = e.getLivre();

        if (l.getQuantite() <= 0) {
            throw new RuntimeException("Livre non disponible");
        }

        l.setQuantite(l.getQuantite() - 1);
        livreRepo.save(l);

        e.setValide(true);
        return empruntRepo.save(e);
    }

    // retour
    public Emprunt retour(Long id) {
        Emprunt e = empruntRepo.findById(id).orElseThrow();

        Livre l = e.getLivre();
        l.setQuantite(l.getQuantite() + 1);
        livreRepo.save(l);

        e.setDateRetour(LocalDate.now());
        return empruntRepo.save(e);
    }

    public List<Emprunt> getAll() {
        return empruntRepo.findAll();
    }
}