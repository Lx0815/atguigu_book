CREATE TABLE t_user
(
    `id`       INT AUTO_INCREMENT PRIMARY KEY ,
    `username` VARCHAR(20)  NOT NULL,
    `password` VARCHAR(32)  NOT NULL,
    `email`    VARCHAR(100) NULL ,
    `role`     INT          NULL ,
    CONSTRAINT username
        UNIQUE (username)
);

INSERT INTO bookdb.t_user (id, username, password, email, role) VALUES (1, 'lina', 'ok', 'lina@sina.com.cn', 0);
INSERT INTO bookdb.t_user (id, username, password, email, role) VALUES (2, 'kate', 'ok', 'hello_kate@126.com', 1);
INSERT INTO bookdb.t_user (id, username, password, email, role) VALUES (3, '鸠摩智', 'ok', 'jiujiu@126.com', 0);
INSERT INTO bookdb.t_user (id, username, password, email, role) VALUES (4, '宝2021', 'ok', 'bao2021@sohu.com.cn', 0);
INSERT INTO bookdb.t_user (id, username, password, email, role) VALUES (5, '宝2022', '123', 'bao2022@sohu.com.cn', 0);
INSERT INTO bookdb.t_user (id, username, password, email, role) VALUES (17, '001', '123', '123@123.com', 0);
INSERT INTO bookdb.t_user (id, username, password, email, role) VALUES (19, 'abcdefgh', '123456789', '123456789@qq.com', 0);
INSERT INTO bookdb.t_user (id, username, password, email, role) VALUES (26, '123321123', '123456789', '123456789@gmail.com', 0);