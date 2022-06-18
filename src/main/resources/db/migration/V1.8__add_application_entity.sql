create table if not exists application
(
    id       uuid primary key default gen_random_uuid(),
    description     varchar(255),
    price      int not null default 0,
    status     varchar(255) not null default 'waiting',
    user_id  uuid        not null references users on delete cascade on update cascade,
    part_id uuid         not null references part on delete cascade on update cascade
    );