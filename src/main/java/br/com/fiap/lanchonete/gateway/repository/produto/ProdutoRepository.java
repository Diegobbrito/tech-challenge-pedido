package br.com.fiap.lanchonete.gateway.repository.produto;

import br.com.fiap.lanchonete.api.adapter.ProdutoAdapter;
import br.com.fiap.lanchonete.core.entity.Produto;
import br.com.fiap.lanchonete.gateway.repository.IProdutoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoRepository implements IProdutoRepository {

    private final JpaProdutoRepository repository;

    public ProdutoRepository(JpaProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Produto> buscarTodos() {
        final var produtos = repository.findAll();
        return produtos.stream().map(ProdutoAdapter::toProduto).collect(Collectors.toList());
    }

    @Override
    public List<Produto> buscarPorCategoria(Integer id) {
        final var produtos = repository.findAllByCategoriaId(id);
        return produtos.stream().map(ProdutoAdapter::toProduto).collect(Collectors.toList());
    }

    @Override
    public List<Produto> buscarTodosPorIds(List<Integer> produtoIds) {
        final var produtos= repository.findByIdIn(produtoIds);
        return produtos.stream().map(ProdutoAdapter::toProduto).collect(Collectors.toList());
    }
}
