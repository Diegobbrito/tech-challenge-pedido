package br.com.fiap.pedido.gateway.dataprovider;

import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.PagamentoDtoResponse;

public interface IPagamentoDataProvider {

    PagamentoDtoResponse criarPagamento(Pedido entity);
}
