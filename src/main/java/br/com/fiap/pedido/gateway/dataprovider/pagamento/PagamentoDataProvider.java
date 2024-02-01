package br.com.fiap.pedido.gateway.dataprovider.pagamento;

import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.gateway.dataprovider.IPagamentoDataProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PagamentoDataProvider implements IPagamentoDataProvider {

    private final PagamentoAPIDataProvider dataProvider;

    public PagamentoDataProvider(PagamentoAPIDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public PagamentoDtoResponse criarPagamento(Pedido entity, List<Produto> produtos) {
        return dataProvider.criarPagamento(entity, produtos);
    }
}
