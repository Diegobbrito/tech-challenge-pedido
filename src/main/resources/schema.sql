CREATE TABLE status_pedido (
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE pedidos (
     id INT NOT NULL AUTO_INCREMENT,
     data_criacao DATE,
     valor DECIMAL(10,2) NOT NULL,
     cliente VARCHAR(20),
     status_id INT,
     PRIMARY KEY (id)
);

CREATE TABLE pedido_produto (
    id INT NOT NULL AUTO_INCREMENT,
    pedido_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    PRIMARY KEY (id)
);