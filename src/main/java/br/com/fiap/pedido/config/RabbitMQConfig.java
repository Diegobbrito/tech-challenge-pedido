package br.com.fiap.pedido.config;

import br.com.fiap.pedido.api.adapter.CustomMessageAdapter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new CustomMessageAdapter(objectMapper);
    }
}

