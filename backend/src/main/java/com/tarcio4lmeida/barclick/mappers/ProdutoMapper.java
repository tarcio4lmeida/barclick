package com.tarcio4lmeida.barclick.mappers;

import com.tarcio4lmeida.barclick.dtos.CategoriaDTO;
import com.tarcio4lmeida.barclick.dtos.MesaDTO;
import com.tarcio4lmeida.barclick.dtos.ProdutoDTO;
import com.tarcio4lmeida.barclick.entities.Categoria;
import com.tarcio4lmeida.barclick.entities.Mesa;
import com.tarcio4lmeida.barclick.entities.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    default ProdutoDTO toDto(Produto produto) {
        ProdutoDTO produtoDto = new ProdutoDTO();
        produtoDto.setId(produto.getId());
        produtoDto.setNome(produto.getNome());
        produtoDto.setDescricao(produto.getDescricao());
        produtoDto.setPrice(produto.getPrice());
        produtoDto.setImgUrl(produto.getImgUrl());
        produtoDto.setDate(produto.getDate());

        Set<Categoria> categorias = produto.getCategorias();
        for (Categoria categoria : categorias) {
            CategoriaDTO categoriaDTO = new CategoriaDTO();
            categoriaDTO.setId(categoria.getId());
            categoriaDTO.setNome(categoria.getNome());
            produtoDto.getCategorias().add(categoriaDTO);
        }

        return produtoDto;
    }
    Produto toEntity(ProdutoDTO rodutoDTO);
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ProdutoDTO dto, @MappingTarget Produto entity);

}
