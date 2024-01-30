package br.com.fiap.pedido.gateway.dataprovider;

import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.PagamentoDtoResponse;

import java.util.List;

public interface IPagamentoDataProvider {

    PagamentoDtoResponse criarPagamento(Pedido entity, List<Produto> produtos);
}
