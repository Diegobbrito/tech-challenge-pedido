package br.com.fiap.lanchonete.gateway.repository.pedido;

import br.com.fiap.lanchonete.api.adapter.PedidoAdapter;
import br.com.fiap.lanchonete.core.entity.Pedido;
import br.com.fiap.lanchonete.gateway.repository.IPedidoRepository;
import org.springframework.stereotype.Component;

@Component
public class PedidoRepository implements IPedidoRepository {

    private final JpaPedidoRepository repository;

    public PedidoRepository(JpaPedidoRepository repository) {
        this.repository = repository;
    }
    @Override
    public Pedido salvar(Pedido pedido) {
        //TODO: Refatorar chamando serviço de produção para criar pedido
        final var pedidoCriado = repository.save(new PedidoEntity(pedido));
        return PedidoAdapter.toPedido(pedidoCriado);
    }

}
