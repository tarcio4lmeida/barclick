package com.tarcio4lmeida.barclick.mappers;

import com.tarcio4lmeida.barclick.dtos.ItemPedidoDTO;
import com.tarcio4lmeida.barclick.dtos.PedidoDTO;
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
            itemPedidoDTO.setProdutoId(item.getProduto().getId());
            itemPedidoDTO.setQuantidade(item.getQuantidade());
            pedidoDTO.getItens().add(itemPedidoDTO);
        }
        return pedidoDTO;
    }

    Pedido toEntity(PedidoDTO pedidoDTO);

}
