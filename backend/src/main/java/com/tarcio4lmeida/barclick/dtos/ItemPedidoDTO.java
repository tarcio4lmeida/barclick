package com.tarcio4lmeida.barclick.dtos;

public class ItemPedidoDTO {

    private Long produtoId;
    private Integer quantidade;

    public ItemPedidoDTO() {
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
