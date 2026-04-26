package com.example.biblio.repository;

import com.example.biblio.model.Bibliothecaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliothecaireRepository extends JpaRepository<Bibliothecaire, Long> {
}