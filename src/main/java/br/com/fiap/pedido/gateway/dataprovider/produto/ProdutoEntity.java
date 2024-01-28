package br.com.fiap.pedido.gateway.dataprovider.produto;

import br.com.fiap.pedido.core.entity.Produto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private String imagemUrl;

    public ProdutoEntity(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.imagemUrl = produto.getImagemUrl();
    }
}
