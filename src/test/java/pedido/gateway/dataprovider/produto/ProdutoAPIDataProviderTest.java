package pedido.gateway.dataprovider.produto;

import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.gateway.dataprovider.produto.ProdutoAPIDataProvider;
import br.com.fiap.pedido.gateway.dataprovider.produto.ProdutoDtoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


class ProdutoAPIDataProviderTest {

    private ProdutoAPIDataProvider produtoDataProvider;
    @Mock
    private RestClient restClient;

    @Mock
    RestClient.RequestBodySpec requestBodySpec;

    @Mock
    RestClient.ResponseSpec responseSpec;

    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    ParameterizedTypeReference<List<ProdutoDtoResponse>> produtosResponse = new ParameterizedTypeReference<>() {};
    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        produtoDataProvider = new ProdutoAPIDataProvider(restClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void testBuscarPedidosPorIds() {
        ProdutoDtoResponse dto = new ProdutoDtoResponse(1,"Hamburguer","Hamburguer da casa","R$ 19.99","");
        List<ProdutoDtoResponse> produtoDtoResponseList = List.of(dto);

        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(any())).thenReturn(requestBodySpec);
        when(requestBodySpec.body((Object) any())).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.toEntity(produtosResponse)).thenReturn(new ResponseEntity<>(produtoDtoResponseList, HttpStatus.OK));

        var response = produtoDataProvider.buscarTodosPorIds(List.of(1));
        assertThat(response)
                .isInstanceOf(List.class)
                .isNotNull();
        assertThat(response.get(0))
                .extracting(Produto::getNome)
                .isEqualTo("Hamburguer");
        assertThat(response.get(0))
                .extracting(Produto::getDescricao)
                .isEqualTo("Hamburguer da casa");
        assertThat(response.get(0))
                .extracting(Produto::getValor)
                .isNotNull();
    }
}
