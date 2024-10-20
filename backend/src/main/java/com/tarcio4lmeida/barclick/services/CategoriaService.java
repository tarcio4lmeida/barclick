package com.tarcio4lmeida.barclick.services;


import com.tarcio4lmeida.barclick.dtos.CategoriaDTO;
import com.tarcio4lmeida.barclick.entities.Categoria;
import com.tarcio4lmeida.barclick.mappers.CategoriaMapper;
import com.tarcio4lmeida.barclick.repositories.CategoriaRepository;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository repository;
    private final CategoriaMapper cagoriaMapper;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        return cagoriaMapper.entitiesToDtos(repository.findAll());
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findbyId(Long id){
        return repository.findById(id)
                .map(cagoriaMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    }
    @Transactional
    public CategoriaDTO insert(CategoriaDTO dto) {
        Categoria entity = new Categoria();
        entity.setNome(dto.getNome());
        entity = repository.save(entity);
        return cagoriaMapper.toDto(entity);
    }

    @Transactional
    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        try {
            Categoria categoria = repository.getOne(id);//atualizar sem precisar ir ao banco 2x
            categoria.setNome(dto.getNome());
            categoria = repository.save(categoria);
            return cagoriaMapper.toDto(categoria);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }

    }
}
