--liquibase formatted sql

--changeset mkhlebnikov:20241127-001
create table if not exists payments
(
    id                  bigserial            primary key,
    user_id              bigint              not null,
    amount              numeric              not null,
    created_at          timestamp            default now()
);
