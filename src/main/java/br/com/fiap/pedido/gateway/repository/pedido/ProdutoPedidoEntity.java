package br.com.fiap.pedido.gateway.repository.pedido;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido_produto")
@Data
@NoArgsConstructor
public class ProdutoPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    private Integer produtoId;

    private Integer quantidade;

    public ProdutoPedidoEntity(PedidoEntity pedido, Integer produtoId, Integer quantidade) {
        this.pedido = pedido;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }
}
