package br.com.fiap.pedido.gateway.messaging;

import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.entity.Produto;

import java.util.List;

public interface IPagamentoQueue {

    void criarPagamento(Pedido entity, List<Produto> produtos);
}
