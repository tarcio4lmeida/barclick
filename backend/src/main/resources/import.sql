INSERT INTO tb_mesa (id, nome, disponivel) VALUES (1, 'Mesa 1', TRUE);
INSERT INTO tb_mesa (id, nome, disponivel) VALUES (2, 'Mesa 2', TRUE);
INSERT INTO tb_mesa (id, nome, disponivel) VALUES (3, 'Mesa 3', TRUE);
INSERT INTO tb_mesa (id, nome, disponivel) VALUES (4, 'Mesa 4', TRUE);
INSERT INTO tb_mesa (id, nome, disponivel) VALUES (5, 'Mesa 5', TRUE);
INSERT INTO tb_mesa (id, nome, disponivel) VALUES (6, 'Mesa 6', TRUE);
INSERT INTO tb_mesa (id, nome, disponivel) VALUES (7, 'Mesa 7', TRUE);
INSERT INTO tb_mesa (id, nome, disponivel) VALUES (8, 'Mesa 8', TRUE);
INSERT INTO tb_mesa (id, nome, disponivel) VALUES (9, 'Mesa 9', TRUE);
INSERT INTO tb_mesa (id, nome, disponivel) VALUES (10, 'Mesa 10', TRUE);
--
INSERT INTO tb_user (nome, email, password) VALUES ('Alex', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (nome, email, password) VALUES ('Maria', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
--
INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
--
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
