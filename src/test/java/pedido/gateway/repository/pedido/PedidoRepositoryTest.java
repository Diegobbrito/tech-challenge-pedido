package pedido.gateway.repository.pedido;

import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.gateway.repository.pedido.JpaPedidoRepository;
import br.com.fiap.pedido.gateway.repository.pedido.PedidoEntity;
import br.com.fiap.pedido.gateway.repository.pedido.PedidoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pedido.utils.PedidoHelper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PedidoRepositoryTest {


    private PedidoRepository pedidoRepository;

    @Mock
    private JpaPedidoRepository jpaRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        pedidoRepository = new PedidoRepository(jpaRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirBuscarTodosOsPedidos() {
        // Arrange
        var pedido = PedidoHelper.gerarPedido();
        when(jpaRepository.findByStatusInOrderByDataCriacaoAsc(anyList())).thenReturn(List.of(new PedidoEntity(pedido)));
        // Act
        var pedidoResponse = pedidoRepository.buscarTodos();
        // Assert
        verify(jpaRepository, times(1)).findByStatusInOrderByDataCriacaoAsc(anyList());
        assertThat(pedidoResponse)
                .isInstanceOf(List.class)
                .isNotNull();
        assertThat(pedidoResponse.get(0))
                .extracting(Pedido::getId)
                .isEqualTo(pedido.getId());
    }

    @Test
    void devePermitirSalverPedido() {
        // Arrange
        var pedido = PedidoHelper.gerarPedido();
        when(jpaRepository.save(any(PedidoEntity.class))).thenReturn(new PedidoEntity(pedido));
        // Act
        var pedidoResponse = pedidoRepository.salvar(pedido);
        // Assert
        verify(jpaRepository, times(1)).save(any(PedidoEntity.class));
        assertThat(pedidoResponse)
                .isInstanceOf(Pedido.class)
                .isNotNull();
        assertThat(pedidoResponse)
                .extracting(Pedido::getId)
                .isEqualTo(pedidoResponse.getId());
    }

    @Test
    void devePermitirAtualizarPedido() {
        // Arrange
        var pedido = PedidoHelper.gerarPedido();
        when(jpaRepository.save(any(PedidoEntity.class))).thenReturn(new PedidoEntity(pedido));
        // Act
        var pedidoResponse = pedidoRepository.atualizar(pedido);
        // Assert
        verify(jpaRepository, times(1)).save(any(PedidoEntity.class));
        assertThat(pedidoResponse)
                .isInstanceOf(Pedido.class)
                .isNotNull();
        assertThat(pedidoResponse)
                .extracting(Pedido::getId)
                .isEqualTo(pedidoResponse.getId());
    }

    @Test
    void devePermitirConsultarPedidoPorId() {
        // Arrange
        var pedido = PedidoHelper.gerarPedido();
        when(jpaRepository.findById(any(Integer.class))).thenReturn(Optional.of(new PedidoEntity(pedido)));
        // Act
        var pedidoResponse = pedidoRepository.buscarPorId(1);
        // Assert
        verify(jpaRepository, times(1)).findById(any(Integer.class));
        assertThat(pedidoResponse)
                .isInstanceOf(Pedido.class)
                .isNotNull();
        assertThat(pedidoResponse)
                .extracting(Pedido::getId)
                .isEqualTo(pedidoResponse.getId());
    }
}
