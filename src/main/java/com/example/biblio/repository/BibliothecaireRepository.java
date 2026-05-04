package com.example.biblio.repository;

import com.example.biblio.model.Bibliothecaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliothecaireRepository extends JpaRepository<Bibliothecaire, Long> {
}