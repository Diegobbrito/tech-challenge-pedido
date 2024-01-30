package br.com.fiap.pedido.gateway.dataprovider.produto;

import br.com.fiap.pedido.api.adapter.ProdutoAdapter;
import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.gateway.dataprovider.IProdutoDataProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class ProdutoAPIDataProvider implements IProdutoDataProvider {

    @Value("${produto.host}")
    private String produtoHost;

    private final RestClient restClient;

    public ProdutoAPIDataProvider(RestClient restClient) {
        this.restClient = restClient;
    }

    ParameterizedTypeReference<List<ProdutoDtoResponse>> produtosResponse = new ParameterizedTypeReference<List<ProdutoDtoResponse>>() {};

    @Override
    public List<Produto> buscarTodosPorIds(List<Integer> produtoIds) {
        StringBuilder ids = new StringBuilder();
        produtoIds.forEach(id -> ids.append(id).append(","));
        ids.deleteCharAt(ids.length()-1);

        var response = restClient.get()
                .uri(produtoHost + "?ids=" + ids.toString())
                .accept(APPLICATION_JSON)
                .retrieve()
                .toEntity(produtosResponse);


        return response.getBody().stream().map(ProdutoAdapter::toProduto).collect(Collectors.toList());
    }
}
