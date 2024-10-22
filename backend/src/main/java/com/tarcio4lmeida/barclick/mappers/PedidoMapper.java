package com.tarcio4lmeida.barclick.mappers;

import com.tarcio4lmeida.barclick.dtos.ItemPedidoDTO;
import com.tarcio4lmeida.barclick.dtos.PedidoDTO;
import com.tarcio4lmeida.barclick.dtos.ProdutoDTO;
import com.tarcio4lmeida.barclick.entities.ItemPedido;
import com.tarcio4lmeida.barclick.entities.Pedido;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    default PedidoDTO toDto(Pedido pedido){
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setMesaId(pedido.getMesa().getId());
        pedidoDTO.setStatus(pedido.getStatus().toString());
        pedidoDTO.setDataCriacao(pedido.getData());
        pedidoDTO.setTotal(pedido.getTotal());

        List<ItemPedido> itens = pedido.getItens();
        for (ItemPedido item: itens){
            ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
            ProdutoDTO produtoDTO = new ProdutoDTO();

            produtoDTO.setId(item.getProduto().getId());
            produtoDTO.setNome(item.getProduto().getNome());
            produtoDTO.setDescricao(item.getProduto().getDescricao());
            produtoDTO.setPrice(item.getProduto().getPrice());
            produtoDTO.setImgUrl(item.getProduto().getImgUrl());
            produtoDTO.setDate(item.getProduto().getDate());

            itemPedidoDTO.setProduto(produtoDTO);
            itemPedidoDTO.setQuantidade(item.getQuantidade());
            pedidoDTO.getItens().add(itemPedidoDTO);
        }
        return pedidoDTO;
    }

    Pedido toEntity(PedidoDTO pedidoDTO);

}
