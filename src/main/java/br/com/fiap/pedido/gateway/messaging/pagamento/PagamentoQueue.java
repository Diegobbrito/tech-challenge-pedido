package br.com.fiap.pedido.gateway.messaging.pagamento;

import br.com.fiap.pedido.api.adapter.PedidoAdapter;
import br.com.fiap.pedido.api.dto.request.PedidoStatusRequest;
import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.core.usecase.pedido.IGerenciarPedido;
import br.com.fiap.pedido.gateway.messaging.IPagamentoQueue;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PagamentoQueue implements IPagamentoQueue {
    private final RabbitTemplate rabbitTemplate;
    private final Gson gson;

    private final IGerenciarPedido gerenciarPedidoUseCase;


    @Value("${queue.pagamentos.pendentes}")
    private String filaPagamentosPendentes;

    public PagamentoQueue(Gson gson, RabbitTemplate rabbitTemplate, IGerenciarPedido gerenciarPedidoUseCase) {
        this.gson = gson;
        this.rabbitTemplate = rabbitTemplate;
        this.gerenciarPedidoUseCase = gerenciarPedidoUseCase;
    }


    @Override
    public void criarPagamento(Pedido entity, List<Produto> produtosList) {
        CriarPagamentoDto dto = new CriarPagamentoDto(
                PedidoAdapter.toRequest(entity.getProdutos(), produtosList),
                entity.getCliente(),
                entity.getValor(),
                entity.getId());

        rabbitTemplate.convertAndSend(filaPagamentosPendentes, gson.toJson(dto));
    }

    @RabbitListener(queues = {"${queue.pedidos.pagos}"})
    public void atualizaPedidoPosPagamento(@Payload String message) {
        PedidoStatusDto dto = gson.fromJson(message, PedidoStatusDto.class);
        gerenciarPedidoUseCase.atualizar(dto.pedidoId(), new PedidoStatusRequest(dto.statusId()));
    }
}