package br.com.fiap.pedido.gateway.repository;

import br.com.fiap.pedido.core.entity.Pedido;

import java.util.List;

public interface IPedidoRepository {
    List<Pedido> buscarTodos();
    Pedido salvar(Pedido pedido);
    Pedido atualizar(Pedido pedido);
    Pedido buscarPorId(Integer pedidoId);
}
