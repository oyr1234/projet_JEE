package com.example.biblio.service;

import com.example.biblio.model.Category;
import com.example.biblio.model.Livre;
import com.example.biblio.repository.CategoryRepository;
import com.example.biblio.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {

    private final LivreRepository repository;
    private final CategoryRepository categoryRepository;

    public LivreService(LivreRepository repository,
                        CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    // ================= CREATE =================
    public Livre save(Livre l) {

        if (l.getCategory() == null || l.getCategory().getId() == null) {
            throw new RuntimeException("Category is required");
        }

        Category category = categoryRepository.findById(l.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        l.setCategory(category);

        return repository.save(l);
    }

    // ================= READ =================
    public List<Livre> getAll() {
        return repository.findAll();
    }

    public Livre getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre not found"));
    }

    // ================= DELETE =================
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // ================= SEARCH =================
    public List<Livre> searchByTitre(String titre) {
        return repository.findByTitreContainingIgnoreCase(titre);
    }

    public List<Livre> searchByAuteur(String auteur) {
        return repository.findByAuteurContainingIgnoreCase(auteur);
    }

    public List<Livre> searchByCategory(String category) {
        return repository.findByCategoryNom(category);
    }

    // ================= FILTER =================
    public List<Livre> availableBooks() {
        return repository.findByDisponible(true);
    }

    public List<Livre> lowStockBooks() {
        return repository.findByQuantiteLessThan(3);
    }

    // ================= UPDATE =================
    public Livre update(Long id, Livre l) {

        Livre existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre not found"));

        existing.setTitre(l.getTitre());
        existing.setAuteur(l.getAuteur());
        existing.setIsbn(l.getIsbn());
        existing.setAnnee(l.getAnnee());
        existing.setQuantite(l.getQuantite());

        if (l.getCategory() != null && l.getCategory().getId() != null) {
            Category category = categoryRepository.findById(l.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existing.setCategory(category);
        } else {
            throw new RuntimeException("Category is required");
        }

        return repository.save(existing);
    }
}