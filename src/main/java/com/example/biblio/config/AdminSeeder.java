package com.example.biblio.config;

import com.example.biblio.model.Role;
import com.example.biblio.model.User;
import com.example.biblio.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminSeeder {

    @Bean
    CommandLineRunner seedAdmin(UserRepository userRepository,
                                PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {

                User admin = new User();
                admin.setNom("Super");
                admin.setPrenom("Admin");
                admin.setEmail("admin@gmail.com");
                admin.setTelephone("11111111");
                admin.setMotDePasse(passwordEncoder.encode("123456"));
                admin.setRole(Role.ADMIN);
                admin.setEnabled(true);

                userRepository.save(admin);

                System.out.println("ADMIN SEEDED SUCCESSFULLY");
                System.out.println("Email: admin@gmail.com");
                System.out.println("Password: 123456");
                System.out.println("Role: ADMIN");

            } else {
                System.out.println("ADMIN ALREADY EXISTS");
            }
        };
    }
}