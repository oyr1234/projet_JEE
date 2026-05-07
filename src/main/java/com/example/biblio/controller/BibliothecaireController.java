package com.example.biblio.controller;

import com.example.biblio.model.Bibliothecaire;
import com.example.biblio.service.BibliothecaireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bibliothecaires")
@CrossOrigin(origins = "https://biblio-six-fawn.vercel.app/")
public class BibliothecaireController {

    private final BibliothecaireService service;

    public BibliothecaireController(BibliothecaireService service) {
        this.service = service;
    }

    @PostMapping
    public Bibliothecaire create(@RequestBody Bibliothecaire b) {
        return service.save(b);
    }

    @GetMapping
    public List<Bibliothecaire> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Bibliothecaire getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    @GetMapping("/test")
    public String test() {
        return "API OK";
    }
}