package com.tarcio4lmeida.barclick.mappers;

import com.tarcio4lmeida.barclick.dtos.MesaDTO;
import com.tarcio4lmeida.barclick.entities.Mesa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MesaMapper {

    MesaDTO toDto(Mesa mesa);

    Mesa toEntity(MesaDTO mesaDTO);

    List<MesaDTO> entitiesToDtos(List<Mesa> mesas);

}
