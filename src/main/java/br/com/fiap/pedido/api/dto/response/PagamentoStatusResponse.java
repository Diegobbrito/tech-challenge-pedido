package br.com.fiap.pedido.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record PagamentoStatusResponse(
        @Schema(example = "Pago")
        String status) {

}
