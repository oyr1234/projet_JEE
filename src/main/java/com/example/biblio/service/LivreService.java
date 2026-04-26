package com.example.biblio.service;

import com.example.biblio.model.Livre;
import com.example.biblio.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {

    private final LivreRepository repository;

    public LivreService(LivreRepository repository) {
        this.repository = repository;
    }

    public Livre save(Livre l) {
        return repository.save(l);
    }

    public List<Livre> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}