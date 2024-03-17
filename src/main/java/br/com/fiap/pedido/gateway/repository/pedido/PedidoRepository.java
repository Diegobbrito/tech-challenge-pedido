package br.com.fiap.pedido.gateway.repository.pedido;

import br.com.fiap.pedido.api.adapter.PedidoAdapter;
import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.enumerator.StatusEnum;
import br.com.fiap.pedido.core.exception.PedidoInexistenteException;
import br.com.fiap.pedido.gateway.repository.IPedidoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoRepository implements IPedidoRepository {

    private final JpaPedidoRepository repository;

    public PedidoRepository(JpaPedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Pedido> buscarTodos() {
        final var pedidos = repository.findByStatusInOrderByDataCriacaoAsc(List.of(
                new StatusPedidoEntity(StatusEnum.PRONTO),
                new StatusPedidoEntity(StatusEnum.PREPARANDO),
                new StatusPedidoEntity(StatusEnum.RECEBIDO)));
        return pedidos.stream().map(PedidoAdapter::toPedido).toList();
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        final var pedidoCriado = repository.save(new PedidoEntity(pedido));
        return PedidoAdapter.toPedido(pedidoCriado);
    }

    @Override
    public Pedido atualizar(Pedido pedido) {
        final var pedidoAtualizado = repository.save(PedidoAdapter.toUpdate(pedido));
        return PedidoAdapter.toPedidoUpdate(pedidoAtualizado);
    }

    @Override
    public Pedido buscarPorId(Integer pedidoId) {
        final var pedido = repository.findById(pedidoId);

        if(pedido.isEmpty()){
            throw new PedidoInexistenteException("Pedido não encontrado");
        }

        return PedidoAdapter.toPedido(pedido.get());
    }

}
