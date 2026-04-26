package com.example.biblio;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.biblio.repository")
public class BiblioApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiblioApplication.class, args);
    }
}