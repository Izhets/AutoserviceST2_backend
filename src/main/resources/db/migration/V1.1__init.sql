create table if not exists users
(
    id      uuid primary key default gen_random_uuid(),
    name    varchar(255)        not null,
    surname varchar(255)        not null,
    phone   varchar(12) unique  not null,
    email   varchar(255) unique not null,
    money   int              default 0
);

create table if not exists car_brand
(
    id   uuid primary key default gen_random_uuid(),
    name varchar(255) unique not null
);

create table if not exists car_model
(
    id       uuid primary key default gen_random_uuid(),
    name     varchar(255) not null,
    brand_id uuid         not null references car_brand on delete cascade on update cascade
);

create table if not exists car_type
(
    id   uuid primary key default gen_random_uuid(),
    type varchar(255) not null
);

create table if not exists car
(
    id       uuid primary key default gen_random_uuid(),
    number   varchar(32) unique not null,
    name     varchar            not null,
    model_id uuid               not null references car_model,
    type_id  uuid               not null references car_type,
    user_id  uuid               not null references users on delete cascade on update cascade
);










