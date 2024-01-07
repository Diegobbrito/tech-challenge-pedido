package br.com.fiap.lanchonete.api.controllers;

import br.com.fiap.lanchonete.api.dto.request.PedidoRequest;
import br.com.fiap.lanchonete.api.dto.response.PedidoResponse;
import br.com.fiap.lanchonete.core.usecase.pedido.ICriarPedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "Pedidos", description = "Criação de pedido")
@RestController
public class PedidoController {
    private final ICriarPedido criarPedidoUseCase;
    public PedidoController(ICriarPedido criarPedidoUseCase) {
        this.criarPedidoUseCase = criarPedidoUseCase;
    }

    @Operation(summary = "Criação de pedidos")
    @PostMapping("/pedidos")
    public ResponseEntity<PedidoResponse> criar(@RequestBody PedidoRequest request){
        final var response = criarPedidoUseCase.criar(request);
        final var uri = URI.create("/pedidos/" + response.getId());
        return ResponseEntity.created(uri).body(response);
    }

}
