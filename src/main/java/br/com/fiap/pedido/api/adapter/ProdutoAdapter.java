package br.com.fiap.pedido.api.adapter;

import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.gateway.dataprovider.produto.ProdutoDtoResponse;

import java.math.BigDecimal;

public class ProdutoAdapter {

    public static Produto toProduto(ProdutoDtoResponse dto) {
        final var produto = new Produto(
                dto.nome(),
                dto.descricao(),
                new BigDecimal(dto.valor().substring(2).replace(',','.').trim()),
                dto.imagemUrl());
        produto.setId(dto.id());
        return produto;
    }
}
