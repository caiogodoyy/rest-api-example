create table users(
    id bigint not null auto_increment,
    active boolean not null,
    username varchar(100) not null unique,
    password varchar(100) not null,
    name varchar(100) not null,
    email varchar(100) not null unique,
    primary key(id)
);