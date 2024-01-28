package br.com.fiap.lanchonete.gateway.dataprovider;

import br.com.fiap.lanchonete.core.entity.Produto;
import java.util.List;

public interface IProdutoDataProvider {
    List<Produto> buscarTodosPorIds(List<Integer> produtoIds);
}
