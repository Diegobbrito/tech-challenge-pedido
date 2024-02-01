package pedido.gateway.dataprovider.pagamento;

import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.enumerator.StatusEnum;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.PagamentoAPIDataProvider;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.PagamentoDtoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClient;
import pedido.utils.PedidoHelper;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


class PagamentoDataAPIProviderTest {

    private PagamentoAPIDataProvider pedidoDataProvider;
    @Mock
    private RestClient restClient;

    @Mock
    RestClient.RequestBodySpec requestBodySpec;

    @Mock
    RestClient.ResponseSpec responseSpec;

    @Mock
    private RestClient.RequestBodyUriSpec requestBodyUriSpec;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        pedidoDataProvider = new PagamentoAPIDataProvider(restClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void testCriarPagamento() {

        ReflectionTestUtils.setField(pedidoDataProvider,"pagamentoHost","pagamentoHost");

        Pedido pedido = PedidoHelper.gerarPedido();
        PagamentoDtoResponse pagamentoDtoResponse = new PagamentoDtoResponse(UUID.randomUUID(), "qrData", StatusEnum.PAGAMENTOPENDENTE, new BigDecimal("19.99"));

        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(any())).thenReturn(requestBodySpec);
        when(requestBodySpec.body((Object) any())).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(PagamentoDtoResponse.class)).thenReturn(new ResponseEntity<>(pagamentoDtoResponse, HttpStatus.OK));

        var pagamento = pedidoDataProvider.criarPagamento(pedido,  List.of(PedidoHelper.gerarProduto()));

        assertThat(pagamento)
                .isInstanceOf(PagamentoDtoResponse.class)
                .isNotNull();
        assertThat(pagamento).extracting(PagamentoDtoResponse::qrData)
                .isEqualTo("qrData");
        assertThat(pagamento).extracting(PagamentoDtoResponse::status)
                .isEqualTo(StatusEnum.PAGAMENTOPENDENTE);
        assertThat(pagamento).extracting(PagamentoDtoResponse::valor)
                .isNotNull();
    }
}
