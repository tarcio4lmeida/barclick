package com.tarcio4lmeida.barclick.repositories;

import com.tarcio4lmeida.barclick.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
