package com.example.biblio.service;

import com.example.biblio.dto.PersonneRequest;
import com.example.biblio.dto.PersonneResponse;
import com.example.biblio.model.Role;
import com.example.biblio.model.User;
import com.example.biblio.repository.EmpruntRepository;
import com.example.biblio.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmpruntRepository empruntRepository; // ✅ ADDED

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       EmpruntRepository empruntRepository) { // ✅ ADDED
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.empruntRepository = empruntRepository; // ✅ ADDED
    }

    // =========================
    // ENTITY -> RESPONSE
    // =========================
    private PersonneResponse toResponse(User user) {
        return new PersonneResponse(
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getTelephone(),
                user.getRole() != null ? user.getRole().name() : null,
                user.isEnabled(),
                user.getDateInscription(),
                null,
                null
        );
    }

    // =========================
    // REQUEST -> ENTITY
    // =========================
    private User toEntity(PersonneRequest request) {
        User user = new User();

        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setEmail(request.getEmail());
        user.setTelephone(request.getTelephone());
        user.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));

        // ✅ respect role from request, default to USER
        if (request.getRole() != null && !request.getRole().isEmpty()) {
            user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        } else {
            user.setRole(Role.USER);
        }

        return user;
    }

    // =========================
    // CREATE USER
    // =========================
    public PersonneResponse addUser(PersonneRequest request) {
        User user = toEntity(request);
        return toResponse(userRepository.save(user));
    }

    // =========================
    // GET ALL USERS
    // =========================
    public List<PersonneResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // =========================
    // GET BY ID
    // =========================
    public PersonneResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toResponse)
                .orElse(null);
    }

    // =========================
    // UPDATE USER
    // =========================
    public PersonneResponse updateUser(Long id, PersonneRequest request) {
        return userRepository.findById(id).map(existing -> {

            existing.setNom(request.getNom());
            existing.setPrenom(request.getPrenom());
            existing.setEmail(request.getEmail());
            existing.setTelephone(request.getTelephone());

            if (request.getMotDePasse() != null && !request.getMotDePasse().isEmpty()) {
                existing.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
            }

            return toResponse(userRepository.save(existing));
        }).orElse(null);
    }

    // =========================
    // DELETE USER ✅ FIXED
    // =========================
    @Transactional
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            empruntRepository.deleteAll(
                    empruntRepository.findByUser(user) // delete loans first
            );
            userRepository.delete(user); // then delete user
        });
    }
}