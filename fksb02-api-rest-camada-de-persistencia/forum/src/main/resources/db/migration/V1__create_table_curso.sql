CREATE TABLE curso (
id int NOT NULL AUTO_INCREMENT,
nome varchar(255) NOT NULL,
categoria varchar(255) NOT NULL,
PRIMARY KEY(id)
);

INSERT INTO curso VALUES (1, 'Kotlin', 'Programação');
INSERT INTO curso VALUES (2, 'Java', 'Programação');
INSERT INTO curso VALUES (3, 'DotNet', 'Programação');
INSERT INTO curso VALUES (4, 'JavaScript', 'Programação');
INSERT INTO curso VALUES (5, 'Python', 'Programação');
INSERT INTO curso VALUES (6, 'Angular', 'Front-end');
INSERT INTO curso VALUES (7, 'React', 'Front-end');
INSERT INTO curso VALUES (8, 'Bootstrap', 'Front-end');
INSERT INTO curso VALUES (9, 'SQL', 'Banco de Dados');
INSERT INTO curso VALUES (10, 'DynamoDB', 'Banco de Dados');