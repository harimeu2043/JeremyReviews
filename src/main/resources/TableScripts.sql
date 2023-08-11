DROP DATABASE IF EXISTS movie_review;

CREATE DATABASE movie_review;

USE movie_review;

create table director(
id integer primary key AUTO_INCREMENT,
name varchar(20),
hair_color varchar(10)
);

create table key_gen(
table_name varchar(20) primary key,
next_val int
);

create table movie(
id integer primary key,
released integer,
name varchar(20),
movie_type varchar (20),
director int references director(id)
);

create table review(
	id integer primary key,
	star_rating integer,
	reviewer varchar(20),
	review varchar(200),
	movie integer references movie(id)
);

insert into key_gen values( "movie", 10);
insert into key_gen values("review", 10);

insert into director values(1, "Eliud", "brown");
insert into director values(2, "Jorgi", "pink");
insert into director values(3, "Bart", "green");

insert into movie values (1, 2022,"Doing dishes", "ACTION", 1);
insert into movie values (2, 2022,"Doing laundry", "ACTION", 2);
insert into movie values (3, 2022,"Cooking dinner", "HORROR", 3);
insert into movie values (4, 2022,"Sweeping", "ACTION", 1);
insert into movie values (5, 2022,"Mowing the Lawn","HORROR", 2);

insert into review values(1, 1, "me", "horrible", 1);
insert into review values(2, 1, "me", "horrible", 1);
insert into review values(3, 1, "me", "horrible", 2);
insert into review values(4, 1, "me", "horrible", 2);
insert into review values(5, 1, "me", "horrible", 3);