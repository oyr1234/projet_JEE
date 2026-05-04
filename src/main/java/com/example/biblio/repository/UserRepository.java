    package com.example.biblio.repository;

    import com.example.biblio.model.User;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.Optional;

    public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByEmail(String email);
    }