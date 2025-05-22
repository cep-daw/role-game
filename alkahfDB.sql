create database alkahf;
use alkahf;

create table partida(
id int auto_increment primary key,
nombre varchar(50) not null,
tiempo int,
monedas int
);