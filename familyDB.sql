create database if not exists family;

use family;

drop table if exists family;

create table famaly (
    id int(10) not null auto_increment,
    last_name varchar(50) not null,
    first_name varchar(50) not null,
    age int(3) not null,
    relation varchar(10) not null,
    primary key(id)

);
