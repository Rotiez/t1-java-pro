create database pract5;

create user pract5_service with password 'pract5_service_password';
create user pract5_service_migration with password 'pract5_service_migration_password';

alter user pract5_service with superuser;
alter user pract5_service_migration with superuser;
