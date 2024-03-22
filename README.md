# Pos Tech Lanchonete

Projeto desenvolvido para o Tech Challenge da Pos Tech FIAP+Alura.

Aplicação para criação e gerenciamento de pedido.

## Saga
-  Coreografa: Escolhemos a Saga Coreografada pela fácil implementação dado o tamanho necessário para o fluxo de pagamento. E não queriamos criar a dependencia de apenas um serviço causar indisponibilidade do fluxo.
-  Utilizado: [Cloudampq](https://www.cloudamqp.com/)
   ![Saga](https://github.com/Diegobbrito/tech-challenge-pedido/blob/main/docs/Saga.png)

## OWASP ZAP
- Os relatorios se encontram na pasta [docs](https://github.com/Diegobbrito/tech-challenge-pedido/tree/main/docs)

## Link da projeto no SonarCloud
-  [SonarCloud - Pedido](https://sonarcloud.io/project/overview?id=Diegobbrito_tech-challenge-pedido)


## Como testar a aplicação com docker compose

Faça download do projeto, crie/atualize o arquivo de variaveis e na pasta principal rode o comando no terminal:

```bash
   docker-compose --env-file *.env up -d
```
No navegador, abra a pagina do Swagger da aplicação:
http://localhost:8080/lanchonete/swagger-ui/index.html#/

O Swagger está documentado com exemplos de request.

## Como testar a aplicação com kubernetes

Faça download do projeto e na pasta principal rode os comandos no terminal para cada arquivo, seguindo a ordem:

Banco de dados:
```bash
   kubectl apply -f ./kubernetes/db/<file>.yaml 
```
Arquivos para banco de dados:

    1. secret.yaml
    2. pv.yaml
    3. pvc.yaml
    4. service.yaml
    5. deployment.yaml
Aplicação:
```bash
   kubectl apply -f ./kubernetes/app/<file>.yaml 
```
Arquivos para aplicação:

    5. service.yaml
    6. deployment.yaml

### Opção de fluxo, usando o swagger

Criar pedido com produtos e quantidade de itens desejados e cliente cadastrado. Para pedidos sem cliente, passar o cpf como nulo ou uma string vazia. ("")
- POST /pedidos

Listar Pedidos - O pedido recem criado não será listado, por não estar pago ainda
- GET /pedidos

Avançar o status do pedido
- PATCH /pedidos/{pedidoId}/atualizar

Id dos status:

2 - Recebido | 3 - Em preparação | 4 - Pronto | 5 - Finalizado

## Stack utilizada

**Banco de dados:** Mysql

**Back-end:** Java, Springboot

**Messageria:** RabbitMQ


## Autores

- [Diego B Brito](https://github.com/Diegobbrito)
- [Gustavo Joia](https://github.com/GustavoJoiaP)
