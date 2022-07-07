CREATE TABLE t_book
(
    `id`            INT                 AUTO_INCREMENT PRIMARY KEY ,
    `book_count`    INT                 NOT NULL ,
    `book_name`     VARCHAR(20)         NOT NULL ,
    `price`         DECIMAL(16, 3)      NOT NULL ,
    `book_img`      VARCHAR(200)        NOT NULL ,
    `author`        VARCHAR(20)         NOT NULL ,
    `sale_count`    INT                 NOT NULL ,
    `book_status`   TINYINT UNSIGNED    DEFAULT '1' NOT NULL
);

INSERT INTO bookdb.t_book (id, book_count, book_name, price, book_img, author, sale_count, book_status) VALUES (1, 186, 'C语言入门经典', 99.000, 'cyuyanrumenjingdian.jpg', '亚历山大', 19, 1);
INSERT INTO bookdb.t_book (id, book_count, book_name, price, book_img, author, sale_count, book_status) VALUES (2, 870, '三体', 48.950, 'santi.jpg', '周杰伦', 40, 1);
INSERT INTO bookdb.t_book (id, book_count, book_name, price, book_img, author, sale_count, book_status) VALUES (3, 125, '艾伦图灵传', 50.000, 'ailuntulingzhuan.jpg', '刘若英', 30, 1);
INSERT INTO bookdb.t_book (id, book_count, book_name, price, book_img, author, sale_count, book_status) VALUES (4, 98, '百年孤独', 40.000, 'bainiangudu.jpg', '王力宏', 3, 1);
INSERT INTO bookdb.t_book (id, book_count, book_name, price, book_img, author, sale_count, book_status) VALUES (5, 93, '边城', 30.000, 'biancheng.jpg', '刘德华', 8, 1);
INSERT INTO bookdb.t_book (id, book_count, book_name, price, book_img, author, sale_count, book_status) VALUES (6, 100, '解忧杂货店', 27.000, 'jieyouzahuodian.jpg', '东野圭吾', 5, 1);
INSERT INTO bookdb.t_book (id, book_count, book_name, price, book_img, author, sale_count, book_status) VALUES (7, 100, '中国哲学史', 45.000, 'zhongguozhexueshi.jpg', '冯友兰', 3, 1);
INSERT INTO bookdb.t_book (id, book_count, book_name, price, book_img, author, sale_count, book_status) VALUES (8, 192, '忽然七日', 19.000, 'huranqiri.jpg', '劳伦', 58, 1);
INSERT INTO bookdb.t_book (id, book_count, book_name, price, book_img, author, sale_count, book_status) VALUES (9, 298, '苏东坡传', 20.000, 'sudongpozhuan.jpg', '林语堂', 52, 1);
INSERT INTO bookdb.t_book (id, book_count, book_name, price, book_img, author, sale_count, book_status) VALUES (11, 99, '给孩子的诗', 23.000, 'geihaizideshi.jpg', '北岛', 5, 1);
INSERT INTO bookdb.t_book (id, book_count, book_name, price, book_img, author, sale_count, book_status) VALUES (10, 89, '扶桑', 20.000, 'fusang.jpg', '严歌岑', 10, 1);
