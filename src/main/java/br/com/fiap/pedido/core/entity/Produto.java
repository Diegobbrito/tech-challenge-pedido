package br.com.fiap.pedido.core.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Produto {
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private String imagemUrl;


    public Produto( String nome, String descricao, BigDecimal valor, String imagemUrl) {
        this.nome = nome.trim();
        this.descricao = descricao.trim();
        this.valor = valor.setScale(2, RoundingMode.HALF_UP);
        this.imagemUrl = imagemUrl;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

}
