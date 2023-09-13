create table students(
    id bigint not null auto_increment,
    active boolean not null,
    name varchar(100) not null,
    email varchar(100) not null unique,
    gender varchar(100) not null,
    grade varchar(100) not null,
    phone varchar(100) not null,
    postal_code varchar(9) not null,
    state varchar(100),
    city varchar(100),
    street varchar(100),
    primary key(id)
);