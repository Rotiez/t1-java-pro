create database payment_service_db;

create user payment_service with password 'payment_service_password';
create user payment_service_migration with password 'payment_service_migration_password';

alter user payment_service with superuser;
alter user payment_service_migration with superuser;



create database product_service_db;

create user product_service with password 'product_service_password';
create user product_service_migration with password 'product_service_migration_password';

alter user product_service with superuser;
alter user product_service_migration with superuser;
