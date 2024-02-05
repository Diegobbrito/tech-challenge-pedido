# Pos Tech Lanchonete

Projeto desenvolvido para o Tech Challenge da Pos Tech FIAP+Alura.
Aplicação para criação e gerenciamento de pedido.

## Link da projeto no SonarCloud
-  [SonarCloud - Pedido](https://sonarcloud.io/project/overview?id=Diegobbrito_tech-challenge-pedido)


## Como testar a aplicação com docker compose

Faça download do projeto e na pasta principal rode o comando no terminal:

```bash
   docker-compose --env-file exemplo.env up -d
```
No navegador, abra a pagina do Swagger da aplicação:
http://localhost:8080/lanchonete/swagger-ui/index.html#/

O Swagger está documentado com exemplos de request.

### Opção de fluxo, usando o swagger

Criar pedido com produtos e quantidade de itens desejados e cliente cadastrado. Para pedidos sem cliente, passar o cpf como nulo ou uma string vazia. ("")
- POST /pedidos

Listar Pedidos - O pedido recem criado não será listado, por não estar pago ainda
- GET /pedidos

Listar Pedidos - O pedido deve ser listado, após etapa de pedido
- GET /pedidos

Avançar o status do pedido
- PATCH /pedidos/{pedidoId}/atualizar

Id dos status:

2 - Recebido | 3 - Em preparação | 4 - Pronto | 5 - Finalizado


A aplicação é iniciada com dados de categorias, produtos e status no banco de dados.
## Stack utilizada

**Banco de dados:** Mysql

**Back-end:** Java, Springboot

## Video
Gravamos um video sobre a nossa quarta parte do projeto:
- https://youtu.be/MrVDanTUrKI

## Autores

- [Diego B Brito](https://github.com/Diegobbrito)
- [Gustavo Joia](https://github.com/GustavoJoiaP)
