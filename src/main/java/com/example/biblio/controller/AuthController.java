    package com.example.biblio.controller;

    import com.example.biblio.dto.LoginRequest;
    import com.example.biblio.dto.LoginResponse;
    import com.example.biblio.dto.PersonneRequest;
    import com.example.biblio.dto.PersonneResponse;
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

        // POST /auth/register/user
        @PostMapping("/register/user")
        public ResponseEntity<?> registerUser(@RequestBody PersonneRequest request) {
            try {
                PersonneResponse response = authService.registerUser(request);
                return ResponseEntity.ok(response);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        // POST /auth/register/admin
        @PostMapping("/register/admin")
        public ResponseEntity<?> registerAdmin(@RequestBody PersonneRequest request) {
            try {
                PersonneResponse response = authService.registerAdmin(request);
                return ResponseEntity.ok(response);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        // POST /auth/register/bibliothecaire
        @PostMapping("/register/bibliothecaire")
        public ResponseEntity<?> registerBibliothecaire(@RequestBody PersonneRequest request) {
            try {
                PersonneResponse response = authService.registerBibliothecaire(request);
                return ResponseEntity.ok(response);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        // POST /auth/login
        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest request) {
            try {
                LoginResponse response = authService.login(request);
                return ResponseEntity.ok(response);
            } catch (RuntimeException e) {
                return ResponseEntity.status(401).body(e.getMessage());
            }
        }
    }