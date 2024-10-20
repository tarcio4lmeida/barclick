package com.tarcio4lmeida.barclick.resources;


import com.tarcio4lmeida.barclick.dtos.CategoriaDTO;
import com.tarcio4lmeida.barclick.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    private final CategoriaService service;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> list  = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
        CategoriaDTO CategoriaDTO = service.findbyId(id);
        return ResponseEntity.ok(CategoriaDTO);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> insert( @RequestBody CategoriaDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        dto = service.insert(dto);
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id,  @RequestBody CategoriaDTO dto) {
        CategoriaDTO CategoriaDTO = service.update(id, dto);
        return ResponseEntity.ok(CategoriaDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
