ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO USERS (ID, NAME, EMAIL, PASSWORD)
VALUES (NEXT VALUE FOR global_seq, 'Admin', 'admin@gmail.com', 'admin'),
       (NEXT VALUE FOR global_seq, 'User', 'user@gmail.com', 'password'),
       (NEXT VALUE FOR global_seq, 'User-vote1', 'user1@gmail.com', 'password1'),
       (NEXT VALUE FOR global_seq, 'User-vote2', 'user2@gmail.com', 'password2');

INSERT INTO USER_ROLES (ROLE, USER_ID)
VALUES ('USER', 100001),
       ('ADMIN', 100000),
       ('USER', 100000);

INSERT INTO RESTAURANT (ID, NAME)
VALUES (NEXT VALUE FOR global_seq, 'Restaurant-1'),
       (NEXT VALUE FOR global_seq, 'Restaurant-2'),
       (NEXT VALUE FOR global_seq, 'Restaurant-3');

INSERT INTO MENU (ID, MENU_DATE, RESTAURANT_ID)
VALUES (NEXT VALUE FOR global_seq, CURRENT_DATE, 100005),
       (NEXT VALUE FOR global_seq, CURRENT_DATE, 100006),
       (NEXT VALUE FOR global_seq, CURRENT_DATE, 100004);

INSERT INTO DISH (ID, NAME, PRICE)
VALUES (NEXT VALUE FOR global_seq, 'Борщ', 100.00),
       (NEXT VALUE FOR global_seq, 'Суп харчо', 120.00),
       (NEXT VALUE FOR global_seq, 'Сырный суп', 150.00),
       (NEXT VALUE FOR global_seq, 'Котлеты по киевски', 600.00),
       (NEXT VALUE FOR global_seq, 'Куриная отбивная', 400.00),
       (NEXT VALUE FOR global_seq, 'Стейк', 1900.00),
       (NEXT VALUE FOR global_seq, 'Компот из вишни', 150.00),
       (NEXT VALUE FOR global_seq, 'Апельсиновый сок', 110.00),
       (NEXT VALUE FOR global_seq, 'Черный чай', 50.00),
       (NEXT VALUE FOR global_seq, 'Пюре', 35.00),
       (NEXT VALUE FOR global_seq, 'Рис', 15.00),
       (NEXT VALUE FOR global_seq, 'Гречка', 10.00);

INSERT INTO MENU_DISH (MENU_ID, DISH_ID)
VALUES (100009, 100010),
       (100009, 100019),
       (100009, 100013),
       (100009, 100016),
       (100007, 100011),
       (100007, 100021),
       (100007, 100014),
       (100007, 100017),
       (100008, 100012),
       (100008, 100020),
       (100008, 100015),
       (100008, 100018);

INSERT INTO VOTE (ID, VOTE_DATE, RESTAURANT_ID, USER_ID)
VALUES (NEXT VALUE FOR global_seq, CURRENT_DATE, 100004, 100003),
       (NEXT VALUE FOR global_seq, CURRENT_DATE, 100005, 100001),
       (NEXT VALUE FOR global_seq, CURRENT_DATE, 100006, 100002);
