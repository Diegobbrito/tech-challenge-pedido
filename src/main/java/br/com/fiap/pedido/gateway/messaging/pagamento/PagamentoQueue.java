package br.com.fiap.pedido.gateway.messaging.pagamento;

import br.com.fiap.pedido.api.adapter.PedidoAdapter;
import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.core.enumerator.StatusEnum;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.CriarPagamentoDto;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.PagamentoDtoResponse;
import br.com.fiap.pedido.gateway.messaging.IPagamentoQueue;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import org.springframework.amqp.support.converter.MessageConverter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class PagamentoQueue implements IPagamentoQueue {

    @Autowired
    private Gson gson;

    private final RabbitTemplate rabbitTemplate;

    private final MessageConverter messageConverter;


    @Value("${queue.pagamentos.pendentes}")
    private String filaPagamentosPendentes;

    public PagamentoQueue(RabbitTemplate rabbitTemplate, MessageConverter messageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = messageConverter;
    }


    @Override
    public void criarPagamento(Pedido entity, List<Produto> produtosList) {
        CriarPagamentoDto dto = new CriarPagamentoDto(
                PedidoAdapter.toRequest(entity.getProdutos(), produtosList),
                entity.getCliente(),
                entity.getValor(),
                entity.getId());

        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.convertAndSend(filaPagamentosPendentes, dto);
    }

    @RabbitListener(queues = {"${queue.pagamentos.criado}"})
    public PagamentoDtoResponse validarPagamentoCriado(@Payload String message) {

        HashMap<String, String> mensagem = gson.fromJson(message, HashMap.class);
        PagamentoDtoResponse pagamentoDto = fromMessageToDto(mensagem);
        return pagamentoDto;
    }

    private static PagamentoDtoResponse fromMessageToDto(Map mensagem) {
        return new PagamentoDtoResponse(
                UUID.fromString(String.valueOf(mensagem.get("id"))),
                (String)mensagem.get("qrData"),
                (StatusEnum) mensagem.get("status"),
                new BigDecimal(String.valueOf(mensagem.get("valor")))
                );
    }
}