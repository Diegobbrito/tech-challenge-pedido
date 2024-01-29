CREATE DATABASE IF NOT EXISTS lanchonete;
USE lanchonete;

CREATE TABLE status_pedido (
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE pedidos (
    id INT NOT NULL AUTO_INCREMENT,
    data_criacao DATE,
    valor DECIMAL(10,2) NOT NULL,
    is_cliente BOOLEAN NOT NULL,
    cliente_id INT,
    status_id INT,
    PRIMARY KEY (id)
);

CREATE TABLE pedido_produto (
    id INT NOT NULL AUTO_INCREMENT,
    pedido_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id),
    PRIMARY KEY (id)
);