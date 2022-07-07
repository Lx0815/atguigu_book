CREATE TABLE t_order
(
    `id`             INT              AUTO_INCREMENT PRIMARY KEY ,
    `order_number`   VARCHAR(128)     NOT NULL ,
    `order_datetime` DATETIME         NULL ,
    `order_user_id`  INT              NULL ,
    `order_money`    DECIMAL(17, 3)   NULL ,
    `order_status`   INT              NULL ,
    CONSTRAINT order_number
        UNIQUE (order_number)
);

INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (4, '5eaab6146dc54e0482fdb8b6120c229b_20211025112519', '2021-10-25 11:25:20', 1, 506.900, 0);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (5, 'f5a22aac925d42eabc6b49c45a3eb74f_20211025113004', '2021-10-25 11:30:04', 1, 48.950, 0);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (6, '8a245df4359e4224b531cf121c4acab3_20211025113019', '2021-10-25 11:30:20', 1, 0.000, 0);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (7, 'b521cd49ab2943f0bbc0630c95978f1c_20211025113039', '2021-10-25 11:30:40', 1, 48.950, 0);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (8, 'd4f366a82cd4491c9899b181753804b4_20211025113151', '2021-10-25 11:31:52', 1, 48.950, 0);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (9, '8f5869a839f4483e947bd2c3163f3c23_20211025113159', '2021-10-25 11:31:59', 1, 48.950, 0);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (10, 'c5fcd95dbe7f49669f96b4ad6444ae6b_20211025120531', '2021-10-25 12:05:32', 1, 147.950, 0);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (11, '6240ec3e5ac04e3583e1beb75a9e94ec_20211025120542', '2021-10-25 12:05:42', 1, 147.950, 0);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (28, '2129422046', '2022-07-01 09:45:46', 1, 92.000, 1);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (33, '89b250e0da52df898ad6c1a446a5cce5', '2022-07-01 12:22:08', 1, 180.000, 1);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (35, '538c0d4afa16f33b7c2840b7da73ae1d', '2022-07-01 17:22:24', 19, 1089.000, 1);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (36, 'b7256c642683ab967e9773916151ed66', '2022-07-01 17:23:00', 19, 489.500, 1);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (37, 'bd1f1b2b002437d535407e034f8241d6', '2022-07-01 17:23:22', 19, 570.000, 1);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (38, '5b8d247e51039a60649e6e3ad6904825', '2022-07-01 19:18:55', 1, 60.000, 1);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (39, '94934f650354966f30cb69744e12e500', '2022-07-02 00:10:10', 26, 152.000, 1);
INSERT INTO bookdb.t_order (id, order_number, order_datetime, order_user_id, order_money, order_status) VALUES (40, '9671a90417b79e07c266df08c92513e1', '2022-07-02 11:40:25', 1, 587.400, 1);