package br.com.fiap.pedido.gateway.dataprovider.pagamento;

import br.com.fiap.pedido.api.adapter.PedidoAdapter;
import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.gateway.dataprovider.IPagamentoDataProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class PagamentoAPIDataProvider implements IPagamentoDataProvider {

    @Value("${pagamento.host}")
    private String pagamentoHost;

    private final RestClient restClient;

    public PagamentoAPIDataProvider(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public PagamentoDtoResponse criarPagamento(Pedido entity) {
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
        if (response.getBody() != null)
            return response.getBody();
        throw new RuntimeException("Erro ao gerar pagamento");
    }

}