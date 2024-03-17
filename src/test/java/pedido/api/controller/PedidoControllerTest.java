package pedido.api.controller;

import br.com.fiap.pedido.PedidoApplication;
import br.com.fiap.pedido.api.controllers.PedidoController;
import br.com.fiap.pedido.api.dto.request.PedidoRequest;
import br.com.fiap.pedido.api.dto.request.PedidoStatusRequest;
import br.com.fiap.pedido.core.exception.PedidoInexistenteException;
import br.com.fiap.pedido.core.usecase.pedido.IBuscarPedido;
import br.com.fiap.pedido.core.usecase.pedido.ICriarPedido;
import br.com.fiap.pedido.core.usecase.pedido.IGerenciarPedido;
import br.com.fiap.pedido.api.handler.RestExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pedido.utils.PedidoHelper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PedidoApplication.class, PedidoControllerTest.class})
class PedidoControllerTest {

    private MockMvc mockMvc;
    @Mock
    private IBuscarPedido buscarPedidoUseCase;
    @Mock
    private ICriarPedido criarPedidoUseCase;
    @Mock
    private IGerenciarPedido gerenciarPedidoUseCase;
    AutoCloseable openMocks;

    PedidoControllerTest(){

    }

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        PedidoController pedidoController = new PedidoController(buscarPedidoUseCase, criarPedidoUseCase, gerenciarPedidoUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController)
                .setControllerAdvice(new RestExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirRegistrarPedido() throws Exception {
        var pedidoRequest = PedidoHelper.gerarPedidoRequest();
        var pedidoResponse = PedidoHelper.gerarPedidoResponse();
        when(criarPedidoUseCase.criar(any(PedidoRequest.class)))
                .thenReturn(pedidoResponse);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pedidoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.valorTotal").value(pedidoResponse.getValorTotal()));

        verify(criarPedidoUseCase, times(1))
                .criar(any(PedidoRequest.class));
    }

    @Test
    void devePermitirConsultarPedidos() throws Exception {
        var pedidoResponse = PedidoHelper.gerarPedidoResponse();
        when(buscarPedidoUseCase.buscarTodos())
                .thenReturn(List.of(pedidoResponse));

        mockMvc.perform(get("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(pedidoResponse.getId()))
                .andExpect(jsonPath("$[0].valorTotal").value(pedidoResponse.getValorTotal()));
        verify(buscarPedidoUseCase, times(1)).buscarTodos();
    }

    @Test
    void deveRetornarExceptionAoConsultarPedidoInexistente() throws Exception {
        when(buscarPedidoUseCase.buscarTodos())
                .thenThrow(PedidoInexistenteException.class);

        mockMvc.perform(get("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(buscarPedidoUseCase, times(1)).buscarTodos();
    }

    @Test
    void deveRetornarExceptionAoConsultarPedidoETerErro() throws Exception {
        when(buscarPedidoUseCase.buscarTodos())
                .thenThrow(IllegalArgumentException.class);

        mockMvc.perform(get("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
        verify(buscarPedidoUseCase, times(1)).buscarTodos();
    }

    @Test
    void devePermitirAtualizarPedido() throws Exception {
        var request = new PedidoStatusRequest(2);
        var pedidoResponse = PedidoHelper.gerarPedidoResponse();

        when(gerenciarPedidoUseCase.atualizar(anyInt(), any(PedidoStatusRequest.class)))
                .thenReturn(pedidoResponse);

        mockMvc.perform(patch("/pedidos/{pedidoId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk());
        verify(gerenciarPedidoUseCase, times(1))
                .atualizar(anyInt(), any(PedidoStatusRequest.class));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
