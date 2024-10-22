package com.tarcio4lmeida.barclick.dtos;

public class ItemPedidoDTO {

    private Integer quantidade;

    private ProdutoDTO produto;

    public ItemPedidoDTO() {
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
