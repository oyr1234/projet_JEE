package com.example.biblio.controller;

import com.example.biblio.model.Livre;
import com.example.biblio.service.LivreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livres")
public class LivreController {

    private final LivreService service;

    public LivreController(LivreService service) {
        this.service = service;
    }

    @PostMapping
    public Livre create(@RequestBody Livre l) {
        return service.save(l);
    }

    @GetMapping
    public List<Livre> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // SEARCH BY TITLE
    @GetMapping("/search/title")
    public List<Livre> searchByTitre(@RequestParam String titre) {
        return service.searchByTitre(titre);
    }

    // SEARCH BY AUTHOR
    @GetMapping("/search/auteur")
    public List<Livre> searchByAuteur(@RequestParam String auteur) {
        return service.searchByAuteur(auteur);
    }

    // FILTER BY CATEGORY
    @GetMapping("/category")
    public List<Livre> searchByCategory(@RequestParam String nom) {
        return service.searchByCategory(nom);
    }

    // AVAILABLE BOOKS
    @GetMapping("/available")
    public List<Livre> availableBooks() {
        return service.availableBooks();
    }

    // LOW STOCK ALERT
    @GetMapping("/low-stock")
    public List<Livre> lowStockBooks() {
        return service.lowStockBooks();
    }
}