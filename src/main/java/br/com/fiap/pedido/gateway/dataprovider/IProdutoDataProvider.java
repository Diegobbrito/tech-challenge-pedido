package br.com.fiap.pedido.gateway.dataprovider;

import br.com.fiap.pedido.core.entity.Produto;
import java.util.List;

public interface IProdutoDataProvider {
    List<Produto> buscarTodosPorIds(List<Integer> produtoIds);
}
