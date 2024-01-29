package br.com.fiap.pedido.core.entity;


import br.com.fiap.pedido.core.enumerator.StatusEnum;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {
    private Integer id;
    private List<ProdutoSelecionado> produtos;
    private BigDecimal valor;
    private String cliente;
    private Status status;
    public Pedido(List<ProdutoSelecionado> produtos, String cliente, Status status) {
        this.cliente = cliente;
        this.valor = BigDecimal.ZERO;
        calculaValorTotalDoPedido(produtos);
        this.produtos = produtos;
        this.status = status;
    }

    private void calculaValorTotalDoPedido(List<ProdutoSelecionado> produtos) {
        produtos.forEach(p ->
            this.valor = this.valor.add(p.getProduto().getValor().multiply(BigDecimal.valueOf(p.getQuantidade())))
        );
    }

    public void setStatus(StatusEnum status) {
        this.status = new Status(StatusEnum.from(status.getId()));
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public List<ProdutoSelecionado> getProdutos() {
        return produtos;
    }
    public BigDecimal getValor() {
        return valor;
    }

    public String getCliente() {
        return cliente;
    }


    public Status getStatus() {
        return status;
    }
}
