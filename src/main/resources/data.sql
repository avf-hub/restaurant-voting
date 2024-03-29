ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO USERS (ID, NAME, EMAIL, PASSWORD)
VALUES (NEXT VALUE FOR global_seq, 'Admin', 'admin@gmail.com', '{noop}admin'),
       (NEXT VALUE FOR global_seq, 'User', 'user@gmail.com', '{noop}password'),
       (NEXT VALUE FOR global_seq, 'User-vote1', 'user1@gmail.com', '{noop}password1'),
       (NEXT VALUE FOR global_seq, 'User-vote2', 'user2@gmail.com', '{noop}password2');

INSERT INTO USER_ROLES (ROLE, USER_ID)
VALUES ('USER', 100001),
       ('ADMIN', 100000),
       ('USER', 100000),
       ('USER', 100002),
       ('USER', 100003);

INSERT INTO RESTAURANT (ID, NAME)
VALUES (NEXT VALUE FOR global_seq, 'Restaurant-1'),
       (NEXT VALUE FOR global_seq, 'Restaurant-2'),
       (NEXT VALUE FOR global_seq, 'Restaurant-3');

INSERT INTO DISH (ID, NAME, DISH_DATE, PRICE, RESTAURANT_ID)
VALUES (NEXT VALUE FOR global_seq, 'Борщ', CURRENT_DATE, 100, 100004),
       (NEXT VALUE FOR global_seq, 'Суп харчо', CURRENT_DATE, 120, 100006),
       (NEXT VALUE FOR global_seq, 'Сырный суп', CURRENT_DATE, 150, 100005),
       (NEXT VALUE FOR global_seq, 'Котлеты по киевски', CURRENT_DATE, 600, 100004),
       (NEXT VALUE FOR global_seq, 'Куриная отбивная', CURRENT_DATE, 400, 100006),
       (NEXT VALUE FOR global_seq, 'Стейк', CURRENT_DATE, 1900, 100005),
       (NEXT VALUE FOR global_seq, 'Компот из вишни', CURRENT_DATE, 150, 100005),
       (NEXT VALUE FOR global_seq, 'Апельсиновый сок', CURRENT_DATE, 110, 100006),
       (NEXT VALUE FOR global_seq, 'Черный чай', CURRENT_DATE, 50, 100004),
       (NEXT VALUE FOR global_seq, 'Пюре', CURRENT_DATE, 35, 100004),
       (NEXT VALUE FOR global_seq, 'Рис', CURRENT_DATE, 15, 100006),
       (NEXT VALUE FOR global_seq, 'Гречка', CURRENT_DATE, 10, 100005);

INSERT INTO VOTE (ID, VOTE_DATE, RESTAURANT_ID, USER_ID)
VALUES (NEXT VALUE FOR global_seq, CURRENT_DATE, 100004, 100003),
       (NEXT VALUE FOR global_seq, CURRENT_DATE, 100005, 100001),
       (NEXT VALUE FOR global_seq, CURRENT_DATE, 100006, 100002),
       (NEXT VALUE FOR global_seq, '2023-02-05', 100006, 100003),
       (NEXT VALUE FOR global_seq, '2023-01-28', 100006, 100003);
