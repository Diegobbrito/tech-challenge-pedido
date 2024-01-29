package br.com.fiap.pedido.core.usecase.pedido;

import br.com.fiap.pedido.api.dto.response.PedidoResponse;

import java.util.List;

public interface IBuscarPedido {
    List<PedidoResponse> buscarTodos();
}
