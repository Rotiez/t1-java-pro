--liquibase formatted sql

--changeset mkhlebnikov:20241127-001
create table if not exists users
(
    id                  bigserial            primary key,
    first_name          varchar(255)         not null,
    last_name           varchar(255)         not null,
    middle_name         varchar(255)
);

create table if not exists products
(
    id                  bigserial            primary key,
    user_id             bigint               not null references users(id) on delete cascade,
    account             varchar(255)         unique not null,
    balance             numeric              default 0.0,
    product_type        varchar(255)         not null
);
