create table if not exists limits
(
    user_id             bigint               primary key,
    amount_used         numeric              not null,
    amount_limit        numeric              default 10000.00,
    created_at          timestamp            default now(),
    modified_at         timestamp            default null
);
