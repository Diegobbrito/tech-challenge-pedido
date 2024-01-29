package br.com.fiap.pedido.core.usecase.pedido;

import br.com.fiap.pedido.api.dto.request.PedidoStatusRequest;
import br.com.fiap.pedido.api.dto.response.PedidoResponse;

public interface IGerenciarPedido {

    PedidoResponse atualizar(Integer pedidoId, PedidoStatusRequest request);
}
