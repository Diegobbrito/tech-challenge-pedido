package br.com.fiap.pedido.gateway.messaging;

import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.PagamentoDtoResponse;

import java.util.List;

public interface IPagamentoQueue {

    PagamentoDtoResponse criarPagamento(Pedido entity, List<Produto> produtos);
}
