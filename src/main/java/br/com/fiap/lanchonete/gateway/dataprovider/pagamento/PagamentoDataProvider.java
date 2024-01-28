package br.com.fiap.lanchonete.gateway.dataprovider.pagamento;

import br.com.fiap.lanchonete.core.entity.Pedido;
import br.com.fiap.lanchonete.gateway.dataprovider.IPagamentoDataProvider;
import org.springframework.stereotype.Component;

@Component
public class PagamentoDataProvider implements IPagamentoDataProvider {

    private final PagamentoAPIDataProvider dataProvider;

    public PagamentoDataProvider(PagamentoAPIDataProvider repository) {
        this.dataProvider = repository;
    }

    @Override
    public String criarPagamento(Pedido entity) {
        return dataProvider.criarPagamento(entity);
    }
}
