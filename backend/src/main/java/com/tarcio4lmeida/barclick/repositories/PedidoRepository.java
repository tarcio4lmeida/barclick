package com.tarcio4lmeida.barclick.repositories;

import com.tarcio4lmeida.barclick.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("SELECT p FROM Pedido p JOIN FETCH p.itens WHERE p.mesa.id = :mesaId AND p.status != com.tarcio4lmeida.barclick.entities.StatusPedido.FINALIZADO")
    List<Pedido> findByMesaAndStatusNotFinalizado(@Param("mesaId") Long mesaId);
}
