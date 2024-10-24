package com.tarcio4lmeida.barclick.dtos;

import java.util.List;

public class CriarAtualizarPedidoDTO {

    private Long mesaId;
    private List<ItemPedidoDTO> itens;

    private String descricao;

    // Getters and Setters
    public Long getMesaId() {
        return mesaId;
    }

    public void setMesaId(Long mesaId) {
        this.mesaId = mesaId;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}