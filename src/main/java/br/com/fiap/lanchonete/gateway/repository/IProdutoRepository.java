package br.com.fiap.lanchonete.gateway.repository;

import br.com.fiap.lanchonete.core.entity.Produto;
import java.util.List;

public interface IProdutoRepository {
    List<Produto> buscarTodos();

    List<Produto> buscarPorCategoria(Integer id);

    List<Produto> buscarTodosPorIds(List<Integer> produtoIds);
}
