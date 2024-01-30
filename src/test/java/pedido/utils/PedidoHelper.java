package pedido.utils;

import br.com.fiap.pedido.api.dto.request.PedidoRequest;
import br.com.fiap.pedido.api.dto.request.PedidoStatusRequest;
import br.com.fiap.pedido.api.dto.request.ProdutoSelecionadoRequest;
import br.com.fiap.pedido.api.dto.response.PedidoResponse;
import br.com.fiap.pedido.api.dto.response.StatusResponse;
import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.core.entity.ProdutoSelecionado;
import br.com.fiap.pedido.core.entity.Status;
import br.com.fiap.pedido.core.enumerator.StatusEnum;

import java.math.BigDecimal;
import java.util.List;

public abstract class PedidoHelper {

    public static PedidoRequest gerarPedidoRequest() {
        ProdutoSelecionadoRequest produtoSelecionado = new ProdutoSelecionadoRequest(1,1);
        List<ProdutoSelecionadoRequest> produtos = List.of(produtoSelecionado);
        return new PedidoRequest(produtos, "");
    }

    public static PedidoResponse gerarPedidoResponse() {
        var response = new PedidoResponse(1, "R$ 19,99","Recebido");
        response.setQrData("qrData");
        return response;
    }

    public static Pedido gerarPedido() {
        Produto produto = gerarProduto();
        ProdutoSelecionado produtoSelecionado = new ProdutoSelecionado(1,1);
        List<ProdutoSelecionado> produtosSelecionados = List.of(produtoSelecionado);
        List<Produto> produtos = List.of(produto);
        String cliente = "";
        Status status = new Status(StatusEnum.PAGAMENTOPENDENTE);

        var pedido = new Pedido(produtosSelecionados, cliente, status, produtos);
        pedido.setId(1);
        return pedido;
    }

    public static Produto gerarProduto(){
        var produto = new Produto("Hambuguer", "Hamburguer da casa", new BigDecimal("19.99"), "");
        produto.setId(1);
        return produto;
    }

    public static PedidoStatusRequest gerarPedidoStatusRequest() {
        return new PedidoStatusRequest(3);
    }
}
