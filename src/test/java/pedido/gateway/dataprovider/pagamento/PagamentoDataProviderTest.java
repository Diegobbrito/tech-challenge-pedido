package pedido.gateway.dataprovider.pagamento;

import br.com.fiap.pedido.api.dto.request.ProdutoSelecionadoRequest;
import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.PagamentoAPIDataProvider;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.PagamentoDataProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pedido.utils.PedidoHelper;

import java.math.BigDecimal;
import java.util.List;

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

        when(dataProvider.criarPagamento(any(Pedido.class))).thenReturn("qrData");

        var pagamento = pedidoDataProvider.criarPagamento(pedido);

        assertThat(pagamento)
                .isInstanceOf(String.class)
                .isNotNull();
        assertThat(pagamento)
                .isEqualTo("qrData");
    }
}
