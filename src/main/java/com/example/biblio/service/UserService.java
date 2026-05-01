package com.example.biblio.service;

import com.example.biblio.dto.PersonneRequest;
import com.example.biblio.dto.PersonneResponse;
import com.example.biblio.model.User;
import com.example.biblio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private PersonneResponse toResponse(User user) {
        return new PersonneResponse(
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getTelephone(),
                user.getRole().name(),
                user.isEnabled(),
                user.getDateInscription(),
                null,
                null
        );
    }

    private User toEntity(PersonneRequest request) {
        return new User(
                request.getNom(),
                request.getPrenom(),
                request.getEmail(),
                request.getTelephone(),
                request.getMotDePasse()
        );
    }

    public PersonneResponse addUser(PersonneRequest request) {
        User user = toEntity(request);
        return toResponse(userRepository.save(user));
    }

    public List<PersonneResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PersonneResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toResponse)
                .orElse(null);
    }

    public PersonneResponse updateUser(Long id, PersonneRequest request) {
        return userRepository.findById(id).map(existing -> {
            existing.setNom(request.getNom());
            existing.setPrenom(request.getPrenom());
            existing.setEmail(request.getEmail());
            existing.setTelephone(request.getTelephone());
            existing.setMotDePasse(request.getMotDePasse());

            return toResponse(userRepository.save(existing));
        }).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}