package pedido.core.usecase.pagamento;

import br.com.fiap.pedido.api.dto.response.PedidoResponse;
import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.usecase.pedido.GerenciarPedidoUseCase;
import br.com.fiap.pedido.gateway.repository.IPedidoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pedido.utils.PedidoHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GerenciarPedidoUseCaseTest {


    private GerenciarPedidoUseCase useCase;

    @Mock
    private IPedidoRepository pedidoRepository;


    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new GerenciarPedidoUseCase(pedidoRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }


    @Test
    void devePermitirAtualizarStatusDoPedido() {
        // Arrange
        var pedidoStatusRequest = PedidoHelper.gerarPedidoStatusRequest();
        var pedido = PedidoHelper.gerarPedido();
        when(pedidoRepository.buscarPorId(any(Integer.class))).thenReturn(pedido);
        when(pedidoRepository.atualizar(any(Pedido.class))).thenReturn(pedido);
        // Act
        var pedidoResponse = useCase.atualizar(1, pedidoStatusRequest);
        // Assert
        verify(pedidoRepository, times(1)).buscarPorId(any(Integer.class));
        verify(pedidoRepository, times(1)).atualizar(any(Pedido.class));
        assertThat(pedidoResponse)
                .isInstanceOf(PedidoResponse.class)
                .isNotNull();
        assertThat(pedidoResponse)
                .extracting(PedidoResponse::getId)
                .isEqualTo(1);
        assertThat(pedidoResponse)
                .extracting(PedidoResponse::getValorTotal)
                .isEqualTo("R$19,99");
    }

}
