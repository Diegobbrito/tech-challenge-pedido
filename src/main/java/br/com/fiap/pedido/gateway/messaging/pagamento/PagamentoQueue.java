package br.com.fiap.pedido.gateway.messaging.pagamento;

import br.com.fiap.pedido.api.adapter.PedidoAdapter;
import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.CriarPagamentoDto;
import br.com.fiap.pedido.gateway.messaging.IPagamentoQueue;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PagamentoQueue implements IPagamentoQueue {

    private final RabbitTemplate rabbitTemplate;

    @Value("${queue.pagamentos.pendentes}")
    private String filaPagamentosPendentes;

    public PagamentoQueue(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = {"${queue.pagamento}"})
    @Override
    public void criarPagamento(Pedido entity, List<Produto> produtosList) {
        List<CriarPagamentoDto.ProdutoDto> produtos = PedidoAdapter.toRequest(entity.getProdutos(), produtosList);
        CriarPagamentoDto dto = new CriarPagamentoDto(
                produtos,
                entity.getCliente(),
                entity.getValor(),
                entity.getId());

        var message = toMessage(dto);

        rabbitTemplate.convertAndSend(filaPagamentosPendentes, message);
    }

    public static String toMessage(CriarPagamentoDto pagamento){
        Map<String, Object> message = new HashMap<>();
        message.put("cpf",pagamento.cpf());
        return new Gson().toJson(message);
    }
}