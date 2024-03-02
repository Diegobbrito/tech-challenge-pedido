package br.com.fiap.pedido.gateway.messaging.pagamento;

import br.com.fiap.pedido.api.adapter.PedidoAdapter;
import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.gateway.dataprovider.IPagamentoDataProvider;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.CriarPagamentoDto;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.PagamentoDtoResponse;
import br.com.fiap.pedido.gateway.messaging.IPagamentoQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class PagamentoQueue implements IPagamentoQueue {

    @Autowired
    public PagamentoQueue(){
    }

    @RabbitListener(queues = {"${queue.pagamento}"})
    @Override
    public PagamentoDtoResponse criarPagamento(Pedido entity, List<Produto> produtos) {
        return null;
    }
}