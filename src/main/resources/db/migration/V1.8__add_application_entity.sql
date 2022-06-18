create table if not exists part_category
(
    id   uuid primary key default gen_random_uuid(),
    name varchar(255) unique not null
    );

create table if not exists part
(
    id       uuid primary key default gen_random_uuid(),
    name     varchar(255) not null,
    category_id uuid         not null references part_category on delete cascade on update cascade
    );

create table if not exists part_on_car
(
    id       uuid primary key default gen_random_uuid(),
    description     varchar(255),
    price      int not null default 0,
    status     varchar(255) not null default 'waiting',
    user_id  uuid        not null references users on delete cascade on update cascade,
    part_id uuid         not null references part on delete cascade on update cascade
    );