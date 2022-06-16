create table if not exists role
(
    id          uuid primary key default gen_random_uuid(),
    name        varchar unique not null,
    description varchar
);

alter table users
    add column role_id  uuid references role,
    add column password varchar