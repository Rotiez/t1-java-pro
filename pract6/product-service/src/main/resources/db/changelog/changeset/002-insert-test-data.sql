--liquibase formatted sql

--changeset mkhlebnikov:20241127-002
insert into users (id, first_name, last_name, middle_name) values
(1, 'Михаил', 'Хлебников', 'Сергеевич'),
(2, 'Иван', 'Иванов', 'Иванович'),
(3, 'Петр', 'Петров', 'Петрович');

insert into products (id, user_id, account, balance, product_type) values
(1, 1, '40702810980420195304', 1000.50, 'ACCOUNT'),
(2, 1, '40702810755423036815', 250.75, 'CARD'),
(3, 2, '40702810849729478287', 500.00, 'ACCOUNT'),
(4, 3, '40702810315492461110', 750.00, 'CARD');
