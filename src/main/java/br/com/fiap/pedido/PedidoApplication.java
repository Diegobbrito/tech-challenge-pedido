package br.com.fiap.pedido;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class PedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidoApplication.class, args);
	}

}
