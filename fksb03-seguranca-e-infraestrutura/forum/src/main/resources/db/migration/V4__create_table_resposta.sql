CREATE TABLE resposta (
id int NOT NULL AUTO_INCREMENT,
mensagem varchar(1020) NOT NULL,
data_criacao DATETIME NOT NULL,
topico_id int NOT NULL,
autor_id int NOT NULL,
solucao boolean NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(topico_id) REFERENCES topico(id),
FOREIGN KEY(autor_id) REFERENCES usuario(id)
);