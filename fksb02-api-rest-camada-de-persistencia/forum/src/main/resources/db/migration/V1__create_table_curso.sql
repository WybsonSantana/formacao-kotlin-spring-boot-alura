CREATE TABLE curso (
id int NOT NULL AUTO_INCREMENT,
nome varchar(255) NOT NULL,
categoria varchar(255) NOT NULL,
PRIMARY KEY(id)
);

INSERT INTO curso VALUES (1, 'Kotlin', 'Programação');