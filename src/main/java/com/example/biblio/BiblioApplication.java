    package com.example.biblio;

    import com.example.biblio.model.Livre;
    import com.example.biblio.repository.LivreRepository;
    import org.springframework.boot.CommandLineRunner;
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.context.annotation.Bean;

    @SpringBootApplication
    public class        BiblioApplication {

        public static void main(String[] args) {
            SpringApplication.run(BiblioApplication.class, args);
        }
    }