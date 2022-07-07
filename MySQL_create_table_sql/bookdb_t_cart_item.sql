CREATE TABLE t_cart_item
(
    `id`        INT             AUTO_INCREMENT PRIMARY KEY ,
    `book`      INT             NULL ,
    `buy_count` INT             NULL ,
    `user_id`   INT             NULL ,
    `all_price` DECIMAL(16, 3)  NOT NULL
);

INSERT INTO bookdb.t_cart_item (id, book, buy_count, user_id, all_price) VALUES (9, 1, 1, 2, 99.000);
INSERT INTO bookdb.t_cart_item (id, book, buy_count, user_id, all_price) VALUES (11, 1, 5, 1, 495.000);
INSERT INTO bookdb.t_cart_item (id, book, buy_count, user_id, all_price) VALUES (13, 3, 2, 1, 100.000);
INSERT INTO bookdb.t_cart_item (id, book, buy_count, user_id, all_price) VALUES (14, 4, 1, 1, 40.000);
INSERT INTO bookdb.t_cart_item (id, book, buy_count, user_id, all_price) VALUES (15, 6, 1, 1, 27.000);
INSERT INTO bookdb.t_cart_item (id, book, buy_count, user_id, all_price) VALUES (16, 7, 1, 1, 45.000);
INSERT INTO bookdb.t_cart_item (id, book, buy_count, user_id, all_price) VALUES (17, 8, 1, 1, 19.000);
INSERT INTO bookdb.t_cart_item (id, book, buy_count, user_id, all_price) VALUES (19, 10, 2, 1, 40.000);
INSERT INTO bookdb.t_cart_item (id, book, buy_count, user_id, all_price) VALUES (26, 5, 4, 19, 120.000);
INSERT INTO bookdb.t_cart_item (id, book, buy_count, user_id, all_price) VALUES (27, 1, 7, 26, 693.000);
INSERT INTO bookdb.t_cart_item (id, book, buy_count, user_id, all_price) VALUES (28, 2, 7, 26, 342.650);