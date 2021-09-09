create table items (
    id serial primary key,
    description varchar(255),
    created timestamp,
    done boolean
);
create table users (
     id serial primary key,
    name varchar(50),
    login varchar(20) unique ,
    password varchar(30)
);
