package com.tarcio4lmeida.barclick.dtos;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RegisterUserDto {
    private String email;
    private String password;
    private String fullName;

    Set<RoleDTO> roles = new HashSet<>();

}