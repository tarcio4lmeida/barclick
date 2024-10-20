package com.tarcio4lmeida.barclick.mappers;

import com.tarcio4lmeida.barclick.dtos.CategoriaDTO;
import com.tarcio4lmeida.barclick.dtos.MesaDTO;
import com.tarcio4lmeida.barclick.entities.Categoria;
import com.tarcio4lmeida.barclick.entities.Mesa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDTO toDto(Categoria categoria);
    Categoria toEntity(CategoriaDTO CategoriaDTO);

    List<CategoriaDTO> entitiesToDtos(List<Categoria> mesas);


}
