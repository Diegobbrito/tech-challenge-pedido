package br.com.fiap.pedido.gateway.dataprovider.pagamento;

import java.math.BigDecimal;
import java.util.List;

public record CriarPagamentoDto(
        List<ProdutoDto> produtos,
        String cpf,
        BigDecimal valor,
        Integer pedidoId
) {
    public record ProdutoDto(
            String nome,
            String descricao,
            BigDecimal valor,
            String imagemUrl
    ) {
    }
}
