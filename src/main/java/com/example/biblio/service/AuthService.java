package com.example.biblio.service;

import com.example.biblio.dto.LoginRequest;
import com.example.biblio.dto.LoginResponse;
import com.example.biblio.dto.PersonneRequest;
import com.example.biblio.model.Bibliothecaire;
import com.example.biblio.model.Role;
import com.example.biblio.model.User;
import com.example.biblio.repository.UserRepository;
import com.example.biblio.security.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       JwtUtils jwtUtils,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(PersonneRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user;

        String role = (request.getRole() == null)
                ? "USER"
                : request.getRole().toUpperCase();

        if ("BIBLIOTHECAIRE".equals(role)) {

            Bibliothecaire bib = new Bibliothecaire();
            bib.setMatricule(request.getMatricule());
            bib.setSalaire(request.getSalaire());
            user = bib;

        } else {

            user = new User();
            user.setRole(Role.USER);
        }

        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setEmail(request.getEmail());
        user.setTelephone(request.getTelephone());
        user.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));

        return userRepository.save(user);
    }

    // =========================
    // LOGIN
    // =========================
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found"));

        if (!passwordEncoder.matches(request.getMotDePasse(), user.getMotDePasse())) {
            throw new RuntimeException("Wrong password");
        }

        String role = user.getRole().name();
        String token = jwtUtils.generateToken(user.getEmail(), role);

        return new LoginResponse(
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                role,
                token
        );
    }
}