package pedido.gateway.dataprovider.pagamento;

import br.com.fiap.pedido.api.dto.request.ProdutoSelecionadoRequest;
import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.enumerator.StatusEnum;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.PagamentoAPIDataProvider;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.PagamentoDataProvider;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.PagamentoDtoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pedido.utils.PedidoHelper;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


class PagamentoDataProviderTest {

    private PagamentoDataProvider pedidoDataProvider;
    @Mock
    private PagamentoAPIDataProvider dataProvider;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        pedidoDataProvider = new PagamentoDataProvider(dataProvider);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void testCriarPagamento() {

        Pedido pedido = PedidoHelper.gerarPedido();
        PagamentoDtoResponse pagamentoDtoResponse = new PagamentoDtoResponse(UUID.randomUUID(), "qrData", StatusEnum.PAGAMENTOPENDENTE, new BigDecimal("19.99"));


        when(dataProvider.criarPagamento(any(Pedido.class))).thenReturn(pagamentoDtoResponse);

        var pagamento = pedidoDataProvider.criarPagamento(pedido);

        assertThat(pagamento)
                .isInstanceOf(PagamentoDtoResponse.class)
                .isNotNull();
        assertThat(pagamento).extracting(PagamentoDtoResponse::qrData)
                .isEqualTo("qrData");
    }
}
