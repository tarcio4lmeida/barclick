package com.tarcio4lmeida.barclick.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MesaDTO {
    private Long id;
    private String nome;
    private Boolean disponivel;
}

