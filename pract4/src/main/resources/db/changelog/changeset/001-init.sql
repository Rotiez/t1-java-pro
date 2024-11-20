--liquibase formatted sql

--changeset mkhlebnikov:20241114-001
create table if not exists users
(
    id                  bigserial            primary key,
    username            varchar(255)         unique not null
);
