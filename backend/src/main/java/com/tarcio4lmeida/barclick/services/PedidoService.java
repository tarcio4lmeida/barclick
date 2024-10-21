package com.tarcio4lmeida.barclick.services;

import com.tarcio4lmeida.barclick.dtos.ItemPedidoDTO;
import com.tarcio4lmeida.barclick.dtos.CriarPedidoDTO;
import com.tarcio4lmeida.barclick.dtos.PedidoDTO;
import com.tarcio4lmeida.barclick.entities.ItemPedido;
import com.tarcio4lmeida.barclick.entities.Pedido;
import com.tarcio4lmeida.barclick.entities.Produto;
import com.tarcio4lmeida.barclick.entities.StatusPedido;
import com.tarcio4lmeida.barclick.mappers.PedidoMapper;
import com.tarcio4lmeida.barclick.repositories.MesaRepository;
import com.tarcio4lmeida.barclick.repositories.PedidoRepository;
import com.tarcio4lmeida.barclick.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final MesaRepository mesaRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    @Transactional
    public PedidoDTO criarPedido(CriarPedidoDTO pedidoDto) {
        Pedido pedido = new Pedido();
        pedido.setMesa(mesaRepository.getReferenceById(pedidoDto.getMesaId()));
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setData(Instant.now());

        double total = 0.0;
        for (ItemPedidoDTO itemDto : pedidoDto.getItens()) {
            Produto produto = produtoRepository.getReferenceById(itemDto.getProdutoId());
            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(itemDto.getQuantidade());
            item.setPreco(produto.getPrice());
            item.setPedido(pedido);

            pedido.getItens().add(item);
            total += produto.getPrice() * itemDto.getQuantidade();
        }
        pedido.setTotal(total);
        pedido = pedidoRepository.save(pedido);

        return pedidoMapper.toDto(pedido);
    }

}
