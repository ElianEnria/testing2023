package com.testing2023.grupo1.repository;

import com.testing2023.grupo1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
}

