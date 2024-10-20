package com.tarcio4lmeida.barclick.services;

import com.tarcio4lmeida.barclick.dtos.MesaDTO;
import com.tarcio4lmeida.barclick.entities.Mesa;
import com.tarcio4lmeida.barclick.mappers.MesaMapper;
import com.tarcio4lmeida.barclick.repositories.MesaRepository;
import com.tarcio4lmeida.barclick.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesaService {
    private final MesaRepository mesaRepository;
    private final MesaMapper mesaMapper;

    public List<MesaDTO> listarMesas() {
        return mesaMapper.entitiesToDtos(mesaRepository.findAll());
    }

    public MesaDTO criarMesa(MesaDTO mesaDTO) {
        if (mesaRepository.existsById(mesaDTO.getId())) {
            throw new IllegalArgumentException("Já existe uma mesa com o ID: " + mesaDTO.getId());
        }

        Mesa mesa = mesaMapper.toEntity(mesaDTO);

        Mesa savedMesa = mesaRepository.save(mesa);
        return mesaMapper.toDto(savedMesa);
    }

    public MesaDTO atualizarMesa(Long id, MesaDTO mesaDTO) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mesa não encontrada"));
        mesa.setNome(mesaDTO.getNome());
        Mesa mesaAtualizada = mesaRepository.save(mesa);
        return mesaMapper.toDto(mesaAtualizada);
    }

    public void removerMesa(Long id) {
        mesaRepository.deleteById(id);
    }
}
