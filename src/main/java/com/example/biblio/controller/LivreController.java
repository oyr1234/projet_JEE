package com.example.biblio.controller;

import com.example.biblio.model.Livre;
import com.example.biblio.service.LivreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livres")
@CrossOrigin(origins = "http://localhost:4200")
public class LivreController {

    private final LivreService service;

    public LivreController(LivreService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<Livre> create(@RequestBody Livre l) {
        return ResponseEntity.ok(service.save(l));
    }

    @GetMapping
    public ResponseEntity<List<Livre>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Livre> update(@PathVariable Long id, @RequestBody Livre l) {
        return ResponseEntity.ok(service.update(id, l));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<Livre>> searchByTitre(@RequestParam String titre) {
        return ResponseEntity.ok(service.searchByTitre(titre));
    }

    @GetMapping("/search/auteur")
    public ResponseEntity<List<Livre>> searchByAuteur(@RequestParam String auteur) {
        return ResponseEntity.ok(service.searchByAuteur(auteur));
    }

    @GetMapping("/category")
    public ResponseEntity<List<Livre>> searchByCategory(@RequestParam String nom) {
        return ResponseEntity.ok(service.searchByCategory(nom));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Livre>> availableBooks() {
        return ResponseEntity.ok(service.availableBooks());
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<Livre>> lowStockBooks() {
        return ResponseEntity.ok(service.lowStockBooks());
    }
}