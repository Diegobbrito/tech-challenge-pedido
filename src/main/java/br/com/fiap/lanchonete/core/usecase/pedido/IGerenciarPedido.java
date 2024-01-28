package br.com.fiap.lanchonete.core.usecase.pedido;

import br.com.fiap.lanchonete.api.dto.request.PedidoStatusRequest;
import br.com.fiap.lanchonete.api.dto.response.PedidoResponse;

public interface IGerenciarPedido {

    PedidoResponse atualizar(Integer pedidoId, PedidoStatusRequest request);
}
