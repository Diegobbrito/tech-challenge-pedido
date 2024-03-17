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

    public Pedido(Integer id, BigDecimal valor, Status status) {
        this.id = id;
        this.valor = valor;
        this.status = status;
    }

    public Pedido(Integer id, List<ProdutoSelecionado> produtos, BigDecimal valor, String cliente, Status status) {
        this.id = id;
        this.produtos = produtos;
        this.valor = valor;
        this.cliente = cliente;
        this.status = status;
    }

    public Pedido(List<ProdutoSelecionado> produtos, String cliente, Status status, List<Produto> produtoList) {
        this.cliente = cliente;
        this.valor = BigDecimal.ZERO;
        calculaValorTotalDoPedido(produtos, produtoList);
        this.produtos = produtos;
        this.status = status;
    }

    private void calculaValorTotalDoPedido(List<ProdutoSelecionado> produtos, List<Produto> produtoList) {
        produtos.forEach(p -> produtoList.forEach(produto -> {
            if(produto.getId().equals(p.getProdutoId())){
                var valorDoProduto = produto.getValor().multiply(BigDecimal.valueOf(p.getQuantidade()));
                this.valor = this.valor.add(valorDoProduto);
            }
        })
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
