CREATE TABLE topico (
id int NOT NULL AUTO_INCREMENT,
titulo varchar(255) NOT NULL,
mensagem varchar(1020) NOT NULL,
data_criacao DATETIME NOT NULL,
status varchar(255) NOT NULL,
curso_id int NOT NULL,
autor_id int NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(curso_id) REFERENCES curso(id),
FOREIGN KEY(autor_id) REFERENCES usuario(id)
);