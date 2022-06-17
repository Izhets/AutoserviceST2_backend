alter table if exists car
    alter column model_id drop not null,
    alter column name drop not null,
    alter column user_id drop not null;

insert into car_type(type) values ('passenger');
insert into car_type(type) values ('truck');
insert into car_type(type) values ('invalid');

insert into car_brand(name) values ('BMW');
insert into car_brand(name) values ('Hyundai');
insert into car_brand(name) values ('Lada');



