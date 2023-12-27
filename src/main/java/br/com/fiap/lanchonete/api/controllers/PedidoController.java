package br.com.fiap.lanchonete.api.controllers;

import br.com.fiap.lanchonete.api.dto.request.PagamentoRequest;
import br.com.fiap.lanchonete.api.dto.request.PedidoRequest;
import br.com.fiap.lanchonete.api.dto.request.PedidoStatusRequest;
import br.com.fiap.lanchonete.api.dto.response.PagamentoStatusResponse;
import br.com.fiap.lanchonete.api.dto.response.PedidoResponse;
import br.com.fiap.lanchonete.core.usecase.pedido.IBuscarPedido;
import br.com.fiap.lanchonete.core.usecase.pedido.ICriarPedido;
import br.com.fiap.lanchonete.core.usecase.pedido.IGerenciarPedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Pedidos", description = "Controle de pedidos")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final IBuscarPedido buscarPedidoUseCase;
    private final ICriarPedido criarPedidoUseCase;
    private final IGerenciarPedido gerenciarPedidoUseCase;
    public PedidoController(IBuscarPedido buscarPedidoUseCase,ICriarPedido criarPedidoUseCase, IGerenciarPedido gerenciarPedidoUseCase) {
        this.buscarPedidoUseCase = buscarPedidoUseCase;
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.gerenciarPedidoUseCase = gerenciarPedidoUseCase;
    }

    @Operation(summary = "Criação de pedidos")
    @PostMapping
    public ResponseEntity<PedidoResponse> criar(@RequestBody PedidoRequest request){
        final var response = criarPedidoUseCase.criar(request);
        final var uri = URI.create("/pedidos/" + response.getId());
        return ResponseEntity.created(uri).body(response);
    }

}
