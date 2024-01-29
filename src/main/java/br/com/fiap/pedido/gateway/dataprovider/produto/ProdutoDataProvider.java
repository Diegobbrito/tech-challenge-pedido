package br.com.fiap.pedido.gateway.dataprovider.produto;

import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.gateway.dataprovider.IProdutoDataProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoDataProvider implements IProdutoDataProvider {

    private final ProdutoAPIDataProvider dataProvider;

    public ProdutoDataProvider(ProdutoAPIDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public List<Produto> buscarTodosPorIds(List<Integer> produtoIds) {
        return dataProvider.buscarTodosPorIds(produtoIds);
    }
}
