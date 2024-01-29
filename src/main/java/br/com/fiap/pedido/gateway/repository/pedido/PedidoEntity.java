package br.com.fiap.pedido.gateway.repository.pedido;

import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.enumerator.StatusEnum;
import br.com.fiap.pedido.gateway.dataprovider.produto.ProdutoEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Entity
@Table(name = "pedidos")
@NoArgsConstructor
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoPedidoEntity> produtos = new ArrayList<>();

    private BigDecimal valor;

    private String cliente;
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusPedidoEntity status;

    public PedidoEntity(Pedido pedido) {
        this.id = pedido.getId();
        this.cliente = pedido.getCliente();
        this.valor = pedido.getValor();
        this.status = new StatusPedidoEntity(StatusEnum.from(pedido.getStatus().getId()));
        this.dataCriacao = LocalDateTime.now();
        this.produtos = pedido.getProdutos().stream().map(p ->
                new ProdutoPedidoEntity(this, new ProdutoEntity(p.getProduto()), p.getQuantidade())
        ).collect(Collectors.toList());
    }

}
