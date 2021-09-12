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
create table categories(
    id serial primary key ,
    name varchar(100)
);
insert into categories(name) values ('личные'), ('семья'), ('друзья'), ('работа'), ('срочные');
