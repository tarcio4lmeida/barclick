package com.tarcio4lmeida.barclick.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_mesa")
public class Mesa {
    @Id
    private Long id;
    private String nome;
    private Boolean disponivel;

    @OneToMany(mappedBy = "mesa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pedido> pedidos = new ArrayList<>();
}
