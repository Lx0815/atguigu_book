CREATE TABLE t_order_item
(
    `id`        INT AUTO_INCREMENT PRIMARY KEY ,
    `book`      INT NULL ,
    `buy_count` INT NULL ,
    `order_id`  INT NULL
);

INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (6, 1, 1, 4);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (7, 2, 2, 4);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (8, 10, 1, 4);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (9, 3, 5, 4);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (10, 4, 1, 4);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (11, 2, 1, 5);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (12, 2, 1, 7);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (13, 2, 1, 8);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (14, 2, 1, 9);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (15, 1, 1, 10);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (16, 2, 1, 10);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (17, 1, 1, 11);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (18, 2, 1, 11);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (31, 11, 4, 28);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (32, 5, 6, 33);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (35, 1, 11, 35);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (36, 2, 10, 36);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (37, 3, 9, 37);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (38, 3, 9, 37);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (39, 9, 1, 38);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (40, 9, 1, 38);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (41, 8, 8, 39);
INSERT INTO bookdb.t_order_item (id, book, buy_count, order_id) VALUES (42, 2, 12, 40);