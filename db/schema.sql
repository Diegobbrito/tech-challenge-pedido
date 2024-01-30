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

SET character_set_client = utf8;
SET character_set_connection = utf8;
SET character_set_results = utf8;
SET collation_connection = utf8_general_ci;

#Status
INSERT INTO status_pedido (titulo) VALUES ("Pagamento Pendente");
INSERT INTO status_pedido (titulo) VALUES ("Recebido");
INSERT INTO status_pedido (titulo) VALUES ("Em preparação");
INSERT INTO status_pedido (titulo) VALUES ("Pronto");
INSERT INTO status_pedido (titulo) VALUES ("Finalizado");

GRANT ALL PRIVILEGES ON *.* TO 'usuario'@'%' WITH GRANT OPTION;
flush privileges;