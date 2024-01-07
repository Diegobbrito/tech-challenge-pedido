package br.com.fiap.lanchonete.gateway.repository;

import br.com.fiap.lanchonete.core.entity.Pedido;


public interface IPedidoRepository {
    Pedido salvar(Pedido pedido);
}
