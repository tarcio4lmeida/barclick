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
--
INSERT INTO tb_categoria (nome, created_At) VALUES ('Entradas', NOW());
INSERT INTO tb_categoria (nome, created_At) VALUES ('Sobremesas', NOW());
INSERT INTO tb_categoria (nome, created_At) VALUES ('Pratos principais', NOW());
INSERT INTO tb_categoria (nome, created_At) VALUES ('Bebidas', NOW());
--
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Fritas', 25.90, NOW(), 'Porção de batata palito frita, bem sequinha e crocante.', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Dadinho de tapioca', 29.90, NOW(), 'Servido com geleia de pimenta especial, feita aqui,', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Torresmo', 14.90, NOW(), 'Individual, pra comer com a feijoada (ou não)!', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Saladinha', 14.90, NOW(), 'Porção individual de salada , com folhas frescas e tomate-cereja.', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg');
--
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (1, 1);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (2, 1);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (3, 1);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (4, 1);
--
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Brigadeiro de colher', 19.90, NOW(), 'Porção de batata palito frita, bem sequinha e crocante.', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Merengue', 22.90, NOW(), 'Morango, suspiros e chantilly servidos em taça.', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Pudim', 15.90, NOW(), 'Sem furinhos, tá? Também feito na casa.', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Frutas da estação', 9.90, NOW(), 'Fatiadas, sem caroço e casca, servidas num pratinho.', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg');
--
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (5, 2);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (6, 2);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (7, 2);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (8, 2);
--
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Bife à cavalo', 28.90, NOW(), 'Filé mignon grelhado, arroz, feijão, farofa e ovo.', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Frango grelhado', 38.90, NOW(), 'Frango grelhado, arroz, feijão, farofa e salada.', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Peixe do dia com legumes', 55.90, NOW(), 'Posta de peixe do dia, arroz e legumes no vapor', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Omelete', 29.90, NOW(), 'Omelete caprichado. arroz, feijão e farofa.', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg');
--
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (9, 3);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (10, 3);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (11, 3);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (12, 3);
--
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Refrigerante', 5.90, NOW(), 'Lata 350ml', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Água', 3.90, NOW(), 'Garrafa 500ml com ou sem gás', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Cerveja', 9.90, NOW(), 'Garrafa 300ml', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Vinho da Casa', 23.90, NOW(), 'Taça 150ml, tinto ou branco', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg');
--
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (13, 4);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (14, 4);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (15, 4);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (16, 4);
--
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Gin Tônica', 31.90, NOW(), 'Gin nacional, água tônica e especiarias', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Caipirinha', 14.00, NOW(), 'Copo 300ml', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/8-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Whisky', 18.00, NOW(), 'Dose 50ml', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/9-big.jpg');
INSERT INTO tb_produto (nome, price, date, descricao, img_url) VALUES ('Cerveja Artesanal', 12.90, NOW(), 'Garrafa 355ml', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/6-big.jpg');
--
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (17, 4);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (18, 4);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (19, 4);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (20, 4);

