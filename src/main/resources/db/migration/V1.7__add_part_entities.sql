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
    part_id uuid         not null references part on delete cascade on update cascade,
    model_id uuid         not null references car_model on delete cascade on update cascade
    );