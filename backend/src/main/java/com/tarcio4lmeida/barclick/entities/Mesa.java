package com.tarcio4lmeida.barclick.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_mesa")
public class Mesa {
    @Id
    private Long id;
    private String nome;
    private Boolean disponivel;
}
