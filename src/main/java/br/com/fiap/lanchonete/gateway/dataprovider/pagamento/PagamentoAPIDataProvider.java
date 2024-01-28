package br.com.fiap.lanchonete.gateway.dataprovider.pagamento;

import br.com.fiap.lanchonete.api.adapter.PedidoAdapter;
import br.com.fiap.lanchonete.core.entity.Pedido;
import br.com.fiap.lanchonete.gateway.dataprovider.IPagamentoDataProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class PagamentoAPIDataProvider implements IPagamentoDataProvider {

    @Value("pagamento.host")
    private String pagamentoHost;

    private final RestClient restClient;

    public PagamentoAPIDataProvider(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public String criarPagamento(Pedido entity) {
        List<CriarPagamentoDto.ProdutoDto> produtos = PedidoAdapter.toRequest(entity.getProdutos());
        CriarPagamentoDto dto = new CriarPagamentoDto(
                produtos,
                entity.getCliente(),
                entity.getValor(),
                entity.getId());
        final var response = restClient.post()
                .uri(pagamentoHost)
                .accept(APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .toEntity(PagamentoDtoResponse.class);
        if (response.getBody() != null && response.getBody().qrData() != null)
            return response.getBody().qrData();
        throw new RuntimeException("Erro ao gerar pagamento");
    }

}