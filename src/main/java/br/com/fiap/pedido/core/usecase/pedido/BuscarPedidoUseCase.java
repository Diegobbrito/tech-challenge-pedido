package br.com.fiap.pedido.core.usecase.pedido;

import br.com.fiap.pedido.api.adapter.PedidoAdapter;
import br.com.fiap.pedido.api.dto.response.PedidoResponse;
import br.com.fiap.pedido.config.UseCase;
import br.com.fiap.pedido.gateway.repository.IPedidoRepository;

import java.util.List;
import java.util.stream.Collectors;
@UseCase
public class BuscarPedidoUseCase implements IBuscarPedido {

    private final IPedidoRepository pedidoRepository;

    public BuscarPedidoUseCase(IPedidoRepository repository) {
        this.pedidoRepository = repository;

    }

    @Override
    public List<PedidoResponse> buscarTodos() {
        final var pedidos = pedidoRepository.buscarTodos();
        return pedidos.stream().map(PedidoAdapter::toResponse).collect(Collectors.toList());
    }
}
