package com.example.biblio.controller;

import com.example.biblio.dto.LoginRequest;
import com.example.biblio.dto.PersonneRequest;
import com.example.biblio.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody PersonneRequest request) {
        try {
            // ✅ Log what backend receives
            System.out.println("=== REGISTER REQUEST ===");
            System.out.println("Nom: "        + request.getNom());
            System.out.println("Prenom: "     + request.getPrenom());
            System.out.println("Email: "      + request.getEmail());
            System.out.println("Role: "       + request.getRole());
            System.out.println("Matricule: "  + request.getMatricule());
            System.out.println("Salaire: "    + request.getSalaire());
            System.out.println("========================");

            return ResponseEntity.ok(authService.register(request));
        } catch (RuntimeException e) {
            System.out.println("=== REGISTER ERROR: " + e.getMessage() + " ===");
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}