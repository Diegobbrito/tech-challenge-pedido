package br.com.fiap.pedido.gateway.messaging.pagamento;

public record PedidoStatusDto(
        Integer pedidoId,
        Integer statusId) {
}
