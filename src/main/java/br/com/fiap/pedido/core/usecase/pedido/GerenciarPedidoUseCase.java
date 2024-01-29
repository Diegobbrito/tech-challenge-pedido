package br.com.fiap.pedido.core.usecase.pedido;

import br.com.fiap.pedido.api.adapter.PedidoAdapter;
import br.com.fiap.pedido.api.dto.request.PedidoStatusRequest;
import br.com.fiap.pedido.api.dto.response.PedidoResponse;
import br.com.fiap.pedido.config.UseCase;
import br.com.fiap.pedido.core.enumerator.StatusEnum;
import br.com.fiap.pedido.core.exception.PedidoStatusException;
import br.com.fiap.pedido.gateway.repository.IPedidoRepository;
@UseCase
public class GerenciarPedidoUseCase implements IGerenciarPedido {

    private final IPedidoRepository pedidoRepository;

    public GerenciarPedidoUseCase(IPedidoRepository repository) {
        this.pedidoRepository = repository;
    }

    @Override
    public PedidoResponse atualizar(Integer pedidoId, PedidoStatusRequest request) {
        final var pedido = pedidoRepository.buscarPorId(pedidoId);
        if(pedido.getStatus().getId().equals(request.statusId()))
            throw new PedidoStatusException("Pedido já está no status: " + pedido.getStatus().getTipo());
        pedido.setStatus(StatusEnum.from(request.statusId()));
        final var entity = pedidoRepository.atualizar(pedido);
        return PedidoAdapter.toResponseUpdate(entity);
    }
}
