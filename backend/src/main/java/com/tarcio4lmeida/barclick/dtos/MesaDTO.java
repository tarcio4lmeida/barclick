package com.tarcio4lmeida.barclick.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MesaDTO {
    private Long id;

    @NotBlank(message = "Nome tem que ser preenchido")
    private String nome;
    private Boolean disponivel;
}

