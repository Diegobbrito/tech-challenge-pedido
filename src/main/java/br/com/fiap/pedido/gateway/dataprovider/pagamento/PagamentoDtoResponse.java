package br.com.fiap.pedido.gateway.dataprovider.pagamento;

import br.com.fiap.pedido.core.enumerator.StatusEnum;

import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoDtoResponse(
        UUID id,
        String qrData,
        StatusEnum status,
        BigDecimal valor
) {
}
