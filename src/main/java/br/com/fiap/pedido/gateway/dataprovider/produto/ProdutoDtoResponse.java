package br.com.fiap.pedido.gateway.dataprovider.produto;

public record ProdutoDtoResponse (
        int id,
        String nome,
        String descricao,
        String valor,
        String imagemUrl){
}
