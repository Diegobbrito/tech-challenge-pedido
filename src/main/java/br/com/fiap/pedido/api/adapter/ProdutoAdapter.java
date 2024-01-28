package br.com.fiap.pedido.api.adapter;

import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.gateway.dataprovider.produto.ProdutoDtoResponse;
import br.com.fiap.pedido.gateway.dataprovider.produto.ProdutoEntity;

import java.math.BigDecimal;

public class ProdutoAdapter {

    public static Produto toProduto(ProdutoEntity produtoEntity) {
        final var produto = new Produto(produtoEntity.getNome(), produtoEntity.getDescricao(), produtoEntity.getValor(), produtoEntity.getImagemUrl());
        produto.setId(produtoEntity.getId());
        return produto;
    }

    public static Produto toProduto(ProdutoDtoResponse dto) {
        final var produto = new Produto(
                dto.nome(),
                dto.descricao(),
                new BigDecimal(dto.valor().substring(2).trim()),
                dto.imagemUrl());
        produto.setId(dto.id());
        return produto;
    }
}
