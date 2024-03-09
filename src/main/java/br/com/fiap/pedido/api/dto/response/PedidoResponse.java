package br.com.fiap.pedido.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class PedidoResponse {

    @Schema(example = "1")
    private Integer id;
    @Schema(example = "R$ 24,90")
    private String valorTotal;
    @Schema(example = "AGUARDANDO PAGAMENTO")
    private String status;

    public PedidoResponse(Integer id, String valor, String status) {
        this.id = id;
        this.valorTotal = valor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public String getStatus() {
        return status;
    }
}
