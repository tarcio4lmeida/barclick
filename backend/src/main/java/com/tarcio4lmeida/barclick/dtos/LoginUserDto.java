package com.tarcio4lmeida.barclick.dtos;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;
    private String password;
}