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
}