package br.com.fiap.pedido.core.usecase.pedido;

import br.com.fiap.pedido.api.dto.request.PedidoRequest;
import br.com.fiap.pedido.api.dto.response.PedidoResponse;

public interface ICriarPedido {
    PedidoResponse criar(PedidoRequest produto);
}
