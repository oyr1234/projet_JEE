package com.example.biblio.service;

import com.example.biblio.model.Bibliothecaire;
import com.example.biblio.repository.BibliothecaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliothecaireService {

    private final BibliothecaireRepository repository;

    public BibliothecaireService(BibliothecaireRepository repository) {
        this.repository = repository;
    }

    public Bibliothecaire save(Bibliothecaire b) {
        return repository.save(b);
    }

    public List<Bibliothecaire> getAll() {
        return repository.findAll();
    }

    public Bibliothecaire getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}