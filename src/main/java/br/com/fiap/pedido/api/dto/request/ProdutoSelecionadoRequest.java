package br.com.fiap.pedido.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record ProdutoSelecionadoRequest(
        @Schema(example = "1")
        Integer produtoId,
        @Schema(example = "1")
        Integer quantidade
) {
}
