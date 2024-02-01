package pedido.gateway.dataprovider.produto;

import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.gateway.dataprovider.produto.ProdutoAPIDataProvider;
import br.com.fiap.pedido.gateway.dataprovider.produto.ProdutoDataProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


class ProdutoDataProviderTest {

    private ProdutoDataProvider produtoDataProvider;
    @Mock
    private ProdutoAPIDataProvider produtoAPIDataProvider;
    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        produtoDataProvider = new ProdutoDataProvider(produtoAPIDataProvider);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void testBuscarPedidosPorIds() {
        Produto dto = new Produto("Hamburguer","Hamburguer da casa", new BigDecimal("19.99"),"");
       var produtoDtoResponseList = List.of(dto);

        when(produtoAPIDataProvider.buscarTodosPorIds(anyList())).thenReturn(produtoDtoResponseList);

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
