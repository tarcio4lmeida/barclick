package com.tarcio4lmeida.barclick.services;

import com.tarcio4lmeida.barclick.dtos.ItemPedidoDTO;
import com.tarcio4lmeida.barclick.dtos.CriarAtualizarPedidoDTO;
import com.tarcio4lmeida.barclick.dtos.PedidoDTO;
import com.tarcio4lmeida.barclick.entities.*;
import com.tarcio4lmeida.barclick.mappers.PedidoMapper;
import com.tarcio4lmeida.barclick.repositories.MesaRepository;
import com.tarcio4lmeida.barclick.repositories.PedidoRepository;
import com.tarcio4lmeida.barclick.repositories.ProdutoRepository;
import com.tarcio4lmeida.barclick.services.exceptions.DatabaseException;
import com.tarcio4lmeida.barclick.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final MesaRepository mesaRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    @Transactional
    public PedidoDTO criarPedido(CriarAtualizarPedidoDTO pedidoDto) {
        Pedido pedido = new Pedido();
        pedido.setMesa(mesaRepository.getReferenceById(pedidoDto.getMesaId()));
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setData(Instant.now());

        BigDecimal total = convertItemsDtoToEntitiesAndGetTotal(pedidoDto, pedido);

        pedido.setTotal(total);
        pedido = pedidoRepository.save(pedido);

        return pedidoMapper.toDto(pedido);
    }

    @Transactional
    public PedidoDTO update(Long id, CriarAtualizarPedidoDTO dto) {
        try {
            Pedido pedido = pedidoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado: " + id));

            Mesa mesaAtualizada = mesaRepository.getReferenceById(dto.getMesaId());
            pedido.setMesa(mesaAtualizada);

            pedido.getItens().clear();

            BigDecimal total = convertItemsDtoToEntitiesAndGetTotal(dto, pedido);
            pedido.setTotal(total);
            Pedido pedidoAtualizado = pedidoRepository.save(pedido);

            return pedidoMapper.toDto(pedidoAtualizado);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Pedido não encontrado para o id " + id);
        }
    }

    public void delete(Long id) {
        try {
            pedidoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Pedido não encontrado para o id " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }

    }
    private BigDecimal convertItemsDtoToEntitiesAndGetTotal(CriarAtualizarPedidoDTO dto, Pedido pedido) {
        double total = 0.0;
        for (ItemPedidoDTO itemDto : dto.getItens()) {
            Produto produto = produtoRepository.getReferenceById(itemDto.getProdutoId());

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemDto.getQuantidade());
            itemPedido.setPreco(produto.getPrice());

            pedido.getItens().add(itemPedido);
            total += produto.getPrice() * itemDto.getQuantidade();
        }
        return new BigDecimal(total).setScale(2, RoundingMode.UP);
    }
}
