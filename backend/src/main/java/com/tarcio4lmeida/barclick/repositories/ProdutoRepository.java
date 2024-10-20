package com.tarcio4lmeida.barclick.repositories;
import com.tarcio4lmeida.barclick.entities.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.tarcio4lmeida.barclick.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT DISTINCT obj FROM Produto obj " +
            "LEFT JOIN obj.categorias cats " +
            "WHERE ((:categorias) IS NULL OR cats IN :categorias) " +
            "AND (LOWER(obj.nome) LIKE LOWER(CONCAT('%', :nome, '%')))")
    Page<Produto> findWithCategories(List<Categoria> categorias, String nome, Pageable pageable);
    Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
