create table studs(
	id serial primary key,
	name varchar(255),
	new boolean,
	age int
);
insert into studs(name, new, age) values('Глеб', true, 20);
update studs set name = 'Kris';
delete from studs;
select * from studs;
