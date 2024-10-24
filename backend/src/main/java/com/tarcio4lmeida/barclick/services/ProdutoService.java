package com.tarcio4lmeida.barclick.services;


import com.tarcio4lmeida.barclick.dtos.ProdutoDTO;
import com.tarcio4lmeida.barclick.entities.Categoria;
import com.tarcio4lmeida.barclick.entities.Produto;
import com.tarcio4lmeida.barclick.mappers.ProdutoMapper;
import com.tarcio4lmeida.barclick.repositories.CategoriaRepository;
import com.tarcio4lmeida.barclick.repositories.ProdutoRepository;
import com.tarcio4lmeida.barclick.services.exceptions.DatabaseException;
import com.tarcio4lmeida.barclick.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;
    private final CategoriaRepository categoryRepository;
    private final ProdutoMapper produtoMapper;
    private final CloudinaryService cloudinaryService;
    private final static String DEFAULT_IMAGEM = "https://clp.org.br/wp-content/uploads/2024/04/default-thumbnail.jpg";

    @Transactional(readOnly = true)
    public Page<ProdutoDTO> findAllPaged(Long categoriaId, String nome, Pageable pageable) {
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
    public ProdutoDTO insert(ProdutoDTO dto, MultipartFile file) {
        log.info("Iniciando a inserção de um produto");
        String imageUrl;
        if (file != null && !file.isEmpty()) {
            try {
                Map uploadResult = cloudinaryService.uploadImage(file);
                imageUrl = (String) uploadResult.get("url");

                log.info("Upload imagem Cloudinary com sucesso");
            } catch (IOException e) {
                log.error("Erro ao fazer upload da imagem: " + e.getMessage());
                imageUrl = DEFAULT_IMAGEM;
            }
        } else {
            imageUrl = DEFAULT_IMAGEM;
            log.warn("Nenhuma imagem fornecida. Usando imagem padrão.");
        }
        dto.setImgUrl(imageUrl);
        dto.setDate(Instant.now());

        Produto produto = produtoMapper.toEntity(dto);
        log.info("Produto {} criado com sucesso", produto.getNome());

        repository.save(produto);
        return produtoMapper.toDto(produto);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto, MultipartFile file) {
        return repository.findById(id).map(existingProduct -> {
            log.info("Iniciando  update de um produto");
            String imageUrl = existingProduct.getImgUrl();
            if (file != null && !file.isEmpty()) {
                try {
                    Map uploadResult = cloudinaryService.uploadImage(file);
                    imageUrl = (String) uploadResult.get("url");
                    log.info("Upload de nova imagem no Cloudinary com sucesso");
                } catch (IOException e) {
                    log.error("Erro ao fazer upload da nova imagem: " + e.getMessage());
                    dto.setImgUrl(imageUrl);
                }
            }
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
