package com.example.biblio.service;

import com.example.biblio.dto.LoginRequest;
import com.example.biblio.dto.LoginResponse;
import com.example.biblio.dto.PersonneRequest;
import com.example.biblio.dto.PersonneResponse;
import com.example.biblio.model.*;
import com.example.biblio.repository.*;
import com.example.biblio.security.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PersonneRepository personneRepository;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final BibliothecaireRepository bibliothecaireRepository;
    private final JwtUtils jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(PersonneRepository personneRepository,
                       UserRepository userRepository,
                       AdminRepository adminRepository,
                       BibliothecaireRepository bibliothecaireRepository,
                       JwtUtils jwtUtil,
                       PasswordEncoder passwordEncoder) {

        this.personneRepository = personneRepository;
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.bibliothecaireRepository = bibliothecaireRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // REGISTER USER
    // =========================
    public PersonneResponse registerUser(PersonneRequest request) {

        if (personneRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User saved = userRepository.save(new User(
                request.getNom(),
                request.getPrenom(),
                request.getEmail(),
                request.getTelephone(),
                passwordEncoder.encode(request.getMotDePasse())
        ));

        // ✅ ROLE FIX
        saved.setRole(Role.USER);
        userRepository.save(saved);

        return new PersonneResponse(
                saved.getId(),
                saved.getNom(),
                saved.getPrenom(),
                saved.getEmail(),
                saved.getTelephone(),
                saved.getRole().name(),
                saved.isEnabled(),
                saved.getDateInscription(),
                null,
                null
        );
    }

    // =========================
    // REGISTER ADMIN
    // =========================
    public PersonneResponse registerAdmin(PersonneRequest request) {

        if (personneRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Admin saved = adminRepository.save(new Admin(
                request.getNom(),
                request.getPrenom(),
                request.getEmail(),
                request.getTelephone(),
                passwordEncoder.encode(request.getMotDePasse())
        ));

        // ✅ ROLE FIX
        saved.setRole(Role.ADMIN);
        adminRepository.save(saved);

        return new PersonneResponse(
                saved.getId(),
                saved.getNom(),
                saved.getPrenom(),
                saved.getEmail(),
                saved.getTelephone(),
                saved.getRole().name(),
                saved.isEnabled(),
                null,
                null,
                null
        );
    }

    // =========================
    // REGISTER BIBLIOTHECAIRE
    // =========================
    public PersonneResponse registerBibliothecaire(PersonneRequest request) {

        if (personneRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Bibliothecaire saved = bibliothecaireRepository.save(new Bibliothecaire(
                request.getNom(),
                request.getPrenom(),
                request.getEmail(),
                request.getTelephone(),
                request.getMatricule(),
                request.getSalaire(),
                passwordEncoder.encode(request.getMotDePasse())
        ));

        // ✅ ROLE FIX
        saved.setRole(Role.BIBLIOTHECAIRE);
        bibliothecaireRepository.save(saved);

        return new PersonneResponse(
                saved.getId(),
                saved.getNom(),
                saved.getPrenom(),
                saved.getEmail(),
                saved.getTelephone(),
                saved.getRole().name(),
                saved.isEnabled(),
                null,
                saved.getMatricule(),
                saved.getSalaire()
        );
    }

    // =========================
    // LOGIN
    // =========================
    public LoginResponse login(LoginRequest request) {

        Personne personne = personneRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found"));

        // password check
        if (!passwordEncoder.matches(request.getMotDePasse(), personne.getMotDePasse())) {
            throw new RuntimeException("Wrong password");
        }

        // ✅ CLEAN ROLE FLOW (NO instanceof)
        String role = personne.getRole().name();

        String token = jwtUtil.generateToken(personne.getEmail(), role);

        return new LoginResponse(
                personne.getId(),
                personne.getNom(),
                personne.getPrenom(),
                personne.getEmail(),
                role,
                token
        );
    }
}