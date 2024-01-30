package pedido.core.usecase.pagamento;

import br.com.fiap.pedido.api.dto.response.PedidoResponse;
import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.core.enumerator.StatusEnum;
import br.com.fiap.pedido.core.usecase.pedido.CriarPedidoUseCase;
import br.com.fiap.pedido.gateway.dataprovider.IPagamentoDataProvider;
import br.com.fiap.pedido.gateway.dataprovider.IProdutoDataProvider;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.PagamentoDtoResponse;
import br.com.fiap.pedido.gateway.repository.IPedidoRepository;
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
import static org.mockito.Mockito.*;

class CriarPedidoUseCaseTest {
    private CriarPedidoUseCase useCase;
    @Mock
    private IPedidoRepository pedidoRepository;
    @Mock
    private IProdutoDataProvider produtoDataProvider;
    @Mock
    private IPagamentoDataProvider pagamentoDataProvider;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new CriarPedidoUseCase(pedidoRepository, produtoDataProvider, pagamentoDataProvider);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }


    @Test
    void devePermitirCriarNovoPedido() {
        // Arrange
        var pedidoRequest = PedidoHelper.gerarPedidoRequest();

        Produto produto = PedidoHelper.gerarProduto();
        Pedido pedido = PedidoHelper.gerarPedido();

        PagamentoDtoResponse pagamentoDtoResponse = new PagamentoDtoResponse(UUID.randomUUID(), "qrData", StatusEnum.PAGAMENTOPENDENTE, new BigDecimal("19.99"));

        when(produtoDataProvider.buscarTodosPorIds(anyList())).thenReturn(List.of(produto));
        when(pedidoRepository.salvar(any(Pedido.class))).thenReturn(pedido);
        when(pagamentoDataProvider.criarPagamento(any(Pedido.class))).thenReturn(pagamentoDtoResponse);
        // Act
        var pagamento = useCase.criar(pedidoRequest);
        // Assert
        verify(produtoDataProvider, times(1)).buscarTodosPorIds(anyList());
        verify(pedidoRepository, times(1)).salvar(any(Pedido.class));
        verify(pagamentoDataProvider, times(1)).criarPagamento(any(Pedido.class));

        assertThat(pagamento)
                .isInstanceOf(PedidoResponse.class)
                .isNotNull();
        assertThat(pagamento)
                .extracting( PedidoResponse::getId)
                .isEqualTo(1);
        assertThat(pagamento)
                .extracting( PedidoResponse::getQrData)
                .isEqualTo("qrData");
    }

}
