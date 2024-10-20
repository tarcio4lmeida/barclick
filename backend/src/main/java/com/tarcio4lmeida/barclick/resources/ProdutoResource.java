package com.tarcio4lmeida.barclick.resources;

import com.tarcio4lmeida.barclick.dtos.ProdutoDTO;
import com.tarcio4lmeida.barclick.services.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/produtos")
public class ProdutoResource {
    private final ProdutoService service;

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findAll(
            @RequestParam(value = "categoriaId", defaultValue = "0") Long categoriaId,
            @RequestParam(value = "nome", defaultValue = "") String nome,
            Pageable pageable) {
        Page<ProdutoDTO> list = service.findAllPaged(categoriaId, nome.trim(), pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
        ProdutoDTO produtoDTO = service.findbyId(id);
        return ResponseEntity.ok(produtoDTO);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> insert(@Valid @RequestBody ProdutoDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        dto = service.insert(dto);
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> update(@Valid @PathVariable Long id, @RequestBody ProdutoDTO dto) {
        ProdutoDTO produtoDTO = service.update(id, dto);
        return ResponseEntity.ok(produtoDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
