package com.tarcio4lmeida.barclick.repositories;

import com.tarcio4lmeida.barclick.entities.Categoria;
import com.tarcio4lmeida.barclick.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
