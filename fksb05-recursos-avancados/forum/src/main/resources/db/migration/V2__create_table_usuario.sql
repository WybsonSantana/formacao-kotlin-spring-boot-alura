CREATE TABLE usuario (
id int NOT NULL AUTO_INCREMENT,
nome varchar(255) NOT NULL,
email varchar(255) NOT NULL,
PRIMARY KEY(id)
);

INSERT INTO usuario VALUES (1, 'Fulano de Tal', 'fulanodetal@mail.com');