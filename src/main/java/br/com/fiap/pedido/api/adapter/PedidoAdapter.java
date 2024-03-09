package br.com.fiap.pedido.api.adapter;

import br.com.fiap.pedido.api.dto.request.PedidoRequest;
import br.com.fiap.pedido.api.dto.response.PedidoResponse;
import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.core.entity.ProdutoSelecionado;
import br.com.fiap.pedido.core.entity.Status;
import br.com.fiap.pedido.core.enumerator.StatusEnum;
import br.com.fiap.pedido.gateway.dataprovider.pagamento.CriarPagamentoDto;
import br.com.fiap.pedido.gateway.repository.pedido.PedidoEntity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PedidoAdapter {

    public static Pedido toPedido(PedidoEntity pedidoEntity) {
        final var status = new Status(StatusEnum.from(pedidoEntity.getStatus().getId()));
        final var produtos = pedidoEntity.getProdutos().stream().map(p ->
                new ProdutoSelecionado(p.getProdutoId(), p.getQuantidade())
        ).toList();
        String cliente = "";
        if(pedidoEntity.getCliente() != null)
            cliente = pedidoEntity.getCliente();
        return new Pedido(pedidoEntity.getId(), produtos,pedidoEntity.getValor(), cliente, status);
    }

    public static PedidoResponse toResponse(Pedido pedido) {
        return  new PedidoResponse(pedido.getId(), formatarParaReal(pedido.getValor()), pedido.getStatus().getTipo());
    }
    public static Pedido toPedido(PedidoRequest request, String cliente, List<Produto> produtos, Status status) {
        return new Pedido(getProdutosSelecionados(request, produtos), cliente, status, produtos);
    }

    private static List<ProdutoSelecionado> getProdutosSelecionados(PedidoRequest request, List<Produto> produtos) {
        Map<Integer, Integer> produtoQuantidade = new HashMap<>();
        request.produtos().forEach(p ->
                produtoQuantidade.put(p.produtoId(), p.quantidade())
        );
        return produtos.stream().map(p -> new ProdutoSelecionado(p.getId(), produtoQuantidade.get(p.getId()))).toList();
    }

    private static String formatarParaReal(BigDecimal valor){
        return "R$" + new DecimalFormat(
                "#,###,##0.00",
                new DecimalFormatSymbols(new Locale("pt", "BR")))
                .format(valor);
    }

    public static PedidoEntity toUpdate(Pedido pedido) {
        return new PedidoEntity(pedido);
    }

    public static PedidoResponse toResponseUpdate(Pedido pedido) {
        return new PedidoResponse(pedido.getId(), formatarParaReal(pedido.getValor()), pedido.getStatus().getTipo());
    }

    public static List<CriarPagamentoDto.ProdutoDto> toRequest(List<ProdutoSelecionado> produtos, List<Produto> produtosList) {
        return produtos.stream().map(produtoSelecionado -> {
            var p = produtosList.stream()
                    .filter(produto -> produtoSelecionado.getProdutoId().equals(produto.getId())).findFirst().get();
            return new CriarPagamentoDto.ProdutoDto(p.getNome(), p.getDescricao(), p.getValor(), p.getImagemUrl());
        }).toList();
    }

}
