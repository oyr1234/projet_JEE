package com.example.biblio.controller;

import com.example.biblio.model.Emprunt;
import com.example.biblio.service.EmpruntService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprunts")
public class EmpruntController {

    private final EmpruntService service;

    public EmpruntController(EmpruntService service) {
        this.service = service;
    }

    // demander emprunt
    @PostMapping("/{livreId}")
    public Emprunt create(@PathVariable Long livreId) {
        return service.create(livreId);
    }

    // valider
    @PutMapping("/valider/{id}")
    public Emprunt valider(@PathVariable Long id) {
        return service.valider(id);
    }

    // retour
    @PutMapping("/retour/{id}")
    public Emprunt retour(@PathVariable Long id) {
        return service.retour(id);
    }

    @GetMapping
    public List<Emprunt> getAll() {
        return service.getAll();
    }
}
