package br.com.fiap.pedido.gateway.messaging;

import br.com.fiap.pedido.core.entity.Produto;

import java.util.List;

public interface IProdutoQueue {
    List<Produto> buscarTodosPorIds(List<Integer> produtoIds);
}
