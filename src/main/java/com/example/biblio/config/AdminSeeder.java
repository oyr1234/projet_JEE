package com.example.biblio.config;

import com.example.biblio.model.Admin;
import com.example.biblio.model.Role;
import com.example.biblio.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminSeeder {

    @Bean
    CommandLineRunner seedAdmin(AdminRepository adminRepository,
                                PasswordEncoder passwordEncoder) {

        return args -> {

            if (adminRepository.findByEmail("admin@gmail.com").isEmpty()) {

                Admin admin = new Admin(
                        "Super",
                        "Admin",
                        "admin@gmail.com",
                        "11111111",
                        passwordEncoder.encode("123456")
                );

                // 🔥 CRITICAL FIX: set role explicitly
                admin.setRole(Role.ADMIN);

                adminRepository.save(admin);

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