# language: pt
Funcionalidade: API - Pedidos

  @smoke
  Cenário: Criar um pedido
    Quando submeter um novo pedido
    Então o pedido é gerado com sucesso

  Cenário: Consultar todos os pedidos
    Dado que um pedido já foi registrado
    Quando requisitar a busca de todos os pedidos
    Então os pedidos são exibidos com sucesso

  Cenário: Atualizar status do pedido
    Quando requisitar a atualização de um pedido
    Então o pedido é atualizado
