package br.com.fiap.pedido.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public class PedidoResponse {

    @Schema(example = "1")
    private Integer id;
    @Schema(example = "R$ 24,90")
    private String valorTotal;
    @Schema(example = "AGUARDANDO PAGAMENTO")
    private String status;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String qrData;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private UUID pagamentoId;

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

    public String getQrData() {
        return qrData;
    }

    public UUID getPagamentoId() {
        return pagamentoId;
    }

    public void setQrData(String qrData) {
        this.qrData = qrData;
    }
    public void setPagamentoId(UUID pagamentoId) {
        this.pagamentoId = pagamentoId;
    }
}
