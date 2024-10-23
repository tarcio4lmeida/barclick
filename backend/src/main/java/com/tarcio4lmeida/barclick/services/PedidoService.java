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
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        Mesa mesa = mesaRepository.getReferenceById(pedidoDto.getMesaId());
        mesa.setDisponivel(false);
        pedido.setMesa(mesa);
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setData(Instant.now());

        BigDecimal total = convertItemsDtoToEntitiesAndGetTotal(pedidoDto, pedido);

        pedido.setTotal(total);
        pedido = pedidoRepository.save(pedido);

        log.info("Pedido {} criado com sucesso ", pedido.getId());
        return pedidoMapper.toDto(pedido);
    }

    @Transactional
    public PedidoDTO update(Long id, CriarAtualizarPedidoDTO dto) {
        try {
            Pedido pedido = pedidoRepository.getReferenceById(id);

            Mesa mesaAtualizada = mesaRepository.getReferenceById(dto.getMesaId());
            pedido.setMesa(mesaAtualizada);

            atualizarItensDoPedido(pedido, dto.getItens());

            BigDecimal total = calcularTotal(pedido);

            pedido.setTotal(total);
            Pedido pedidoAtualizado = pedidoRepository.save(pedido);

            return pedidoMapper.toDto(pedidoAtualizado);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Pedido não encontrado para o id " + id);
        }
    }
    private void atualizarItensDoPedido(Pedido pedido, List<ItemPedidoDTO> novosItens) {
        // Limpar a lista de itens sem removê-los do banco diretamente
        List<ItemPedido> itensAtuais = pedido.getItens();

        // Remover itens que não estão mais no pedido
        itensAtuais.removeIf(item ->
                novosItens.stream().noneMatch(novo -> novo.getProduto().getId().equals(item.getProduto().getId()))
        );

        // Adicionar ou atualizar itens
        for (ItemPedidoDTO itemDto : novosItens) {
            Produto produto = produtoRepository.getReferenceById(itemDto.getProduto().getId());

            ItemPedido itemPedido = itensAtuais.stream()
                    .filter(item -> item.getProduto().getId().equals(produto.getId()))
                    .findFirst()
                    .orElseGet(() -> {
                        ItemPedido novoItem = new ItemPedido();
                        novoItem.setProduto(produto);
                        novoItem.setPedido(pedido);
                        pedido.getItens().add(novoItem);
                        return novoItem;
                    });

            itemPedido.setQuantidade(itemDto.getQuantidade());
            itemPedido.setPreco(produto.getPrice());
        }
    }

    private BigDecimal calcularTotal(Pedido pedido) {
        return pedido.getItens().stream()
                .map(item -> item.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.UP);
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
    @Transactional(readOnly = true)
    public List<PedidoDTO> findNonFinalizedPedidosByMesa(Long mesaId) {
        List<Pedido> pedidos = pedidoRepository.findByMesaAndStatusNotFinalizado(mesaId);
        return pedidos.stream().map(pedidoMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public PedidoDTO marcarComoEntregue(Long id) {
        try {
            // Buscar o pedido pelo ID
            Pedido pedido = pedidoRepository.getReferenceById(id);

            // Verificar se o status atual é PENDENTE
            if (pedido.getStatus() != StatusPedido.PENDENTE) {
                throw new IllegalStateException("O pedido já foi entregue ou está finalizado.");
            }

            // Atualizar o status para ENTREGUE
            pedido.setStatus(StatusPedido.ENTREGUE);

            // Salvar a atualização no banco
            Pedido pedidoAtualizado = pedidoRepository.save(pedido);

            // Retornar o DTO atualizado
            return pedidoMapper.toDto(pedidoAtualizado);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Pedido não encontrado para o id " + id);
        }
    }

    private BigDecimal convertItemsDtoToEntitiesAndGetTotal(CriarAtualizarPedidoDTO dto, Pedido pedido) {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemPedidoDTO itemDto : dto.getItens()) {
            Produto produto = produtoRepository.getReferenceById(itemDto.getProduto().getId());

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemDto.getQuantidade());
            itemPedido.setPreco(produto.getPrice());
            itemPedido.setPedido(pedido);
            pedido.getItens().add(itemPedido);
            total = total.add(produto.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantidade())));
        }
        return total.setScale(2, RoundingMode.UP);
    }
}
