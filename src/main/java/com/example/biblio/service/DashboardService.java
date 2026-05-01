package com.example.biblio.service;

import com.example.biblio.dto.DashboardResponse;
import com.example.biblio.repository.*;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final LivreRepository livreRepository;
    private final EmpruntRepository empruntRepository;
    private final BibliothecaireRepository bibliothecaireRepository;

    public DashboardService(
            UserRepository userRepository,
            LivreRepository livreRepository,
            EmpruntRepository empruntRepository,
            BibliothecaireRepository bibliothecaireRepository
    ) {
        this.userRepository = userRepository;
        this.livreRepository = livreRepository;
        this.empruntRepository = empruntRepository;
        this.bibliothecaireRepository = bibliothecaireRepository;
    }

    public DashboardResponse getDashboardStats() {

        long totalUsers = userRepository.count();
        long totalBooks = livreRepository.count();
        long totalEmprunts = empruntRepository.count();

        long pendingEmprunts =
                empruntRepository.findByValide(false).size();

        long returnedBooks =
                empruntRepository.findByRetourne(true).size();

        long overdueBooks =
                empruntRepository.findByEnRetard(true).size();

        long totalBibliothecaires =
                bibliothecaireRepository.count();

        long availableBooks =
                livreRepository.findByDisponible(true).size();

        long lowStockBooks =
                livreRepository.findByQuantiteLessThan(3).size();

        return new DashboardResponse(
                totalUsers,
                totalBooks,
                totalEmprunts,
                pendingEmprunts,
                returnedBooks,
                overdueBooks,
                totalBibliothecaires,
                availableBooks,
                lowStockBooks
        );
    }
}