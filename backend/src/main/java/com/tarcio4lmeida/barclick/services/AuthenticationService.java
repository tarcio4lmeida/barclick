package com.tarcio4lmeida.barclick.services;

import com.tarcio4lmeida.barclick.dtos.LoginUserDto;
import com.tarcio4lmeida.barclick.dtos.RegisterUserDto;
import com.tarcio4lmeida.barclick.entities.Role;
import com.tarcio4lmeida.barclick.entities.User;
import com.tarcio4lmeida.barclick.repositories.RoleRepository;
import com.tarcio4lmeida.barclick.repositories.UsuarioRepository;
import com.tarcio4lmeida.barclick.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;


    public User signup(RegisterUserDto input) {
        var user = new User()
                .setNome(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()));

        List<Role> roles = input.getRoles().stream()
                .map(roleDto -> roleRepository.findById(roleDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Role não encontrada: " + roleDto.getId()))).toList();

       roles.forEach(r -> user.getRoles().add(r));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getEmail(),
                input.getPassword()
            )
        );

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    public List<User> allUsers() {
        return new ArrayList<>(userRepository.findAll());
    }
}
