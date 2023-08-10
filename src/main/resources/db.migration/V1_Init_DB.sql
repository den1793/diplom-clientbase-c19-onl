/*create table users
(
    id           serial         not null primary key unique,
    name         varchar        not null,
    lastname     varchar        not null,
    username     varchar unique not null,
    password     varchar        not null,
    position     varchar,
    email        varchar,
    telephones_id serial         not null


);

create table clients
(
    id          serial  not null primary key unique,
    client_name varchar not null,
    address     varchar not null
);

create table fiscals
(
    id     serial  not null primary key unique,
    model  varchar not null,
    serial varchar not null

);

create table tasks
(
    id      serial  not null primary key unique,
    client  varchar not null,
    address varchar not null,
    reason  varchar not null
);


create table users_telephone
(
    id     serial not null primary key unique,
    number varchar

);



*/