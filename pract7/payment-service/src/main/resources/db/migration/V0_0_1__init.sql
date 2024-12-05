create table if not exists payments
(
    id                  bigserial            primary key,
    user_id             bigint               not null,
    amount              numeric              not null,
    created_at          timestamp            default now()
);
