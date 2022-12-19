INSERT INTO usuario(id, nome, email, password)
VALUES(
2,
'admin',
'admin@mail.com',
'$2a$12$3ilD9q6f6DqNeUMM55cffuhmHJ0dqN1rKTSwxNKPvc77iUMI.2sY.'
);

INSERT INTO role(id, nome)
VALUES(2, 'ADMIN');

INSERT INTO usuario_role(id, usuario_id, role_id)
VALUES(2, 2, 2);