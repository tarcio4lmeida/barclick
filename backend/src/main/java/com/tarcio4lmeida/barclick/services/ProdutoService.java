package com.tarcio4lmeida.barclick.services;


import com.tarcio4lmeida.barclick.dtos.CategoriaDTO;
import com.tarcio4lmeida.barclick.dtos.ProdutoDTO;
import com.tarcio4lmeida.barclick.entities.Categoria;
import com.tarcio4lmeida.barclick.entities.Produto;
import com.tarcio4lmeida.barclick.mappers.ProdutoMapper;
import com.tarcio4lmeida.barclick.repositories.CategoriaRepository;
import com.tarcio4lmeida.barclick.repositories.ProdutoRepository;
import com.tarcio4lmeida.barclick.services.exceptions.DatabaseException;
import com.tarcio4lmeida.barclick.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;
    private final CategoriaRepository categoryRepository;
    private final ProdutoMapper produtoMapper;

    @Transactional(readOnly = true)
    public Page<ProdutoDTO> findAllPaged(Long categoriaId, String nome, Pageable pageable) {
        // Verifica se há categorias a serem filtradas
        if (categoriaId == 0) {
            return repository.findByNomeContainingIgnoreCase(nome, pageable)
                    .map(produtoMapper::toDto);
        } else {
            List<Categoria> categorias = List.of(categoryRepository.getReferenceById(categoriaId));
            Page<Produto> page = repository.findWithCategories(categorias, nome, pageable);
            return page.map(produtoMapper::toDto);
        }
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findbyId(Long id) {
        return repository.findById(id)
                .map(produtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);
        repository.save(produto);
        return produtoMapper.toDto(produto);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        return repository.findById(id).map(existingProduct -> {
            produtoMapper.updateEntityFromDto(dto, existingProduct);
            Produto updatedProduct = repository.save(existingProduct);
            return produtoMapper.toDto(updatedProduct);
        }).orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }

    }
}
