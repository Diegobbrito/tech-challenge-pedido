package br.com.fiap.pedido.core.entity;

public class ProdutoSelecionado {
    private Integer produtoId;
    private Integer quantidade;

    public ProdutoSelecionado(Integer produtoId, Integer quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
