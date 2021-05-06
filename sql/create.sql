create table role(
	id serial primary key,
	role varchar(2000)
);
create table users(
	id serial primary key,
	name varchar(2000),
	role_id int references role(id)
);
create table rules(
	id serial primary key,
	rule varchar(2000)
);
create table role_rules(
	id serial primary key,
	rules_id int references rules(id),
	role_id int references role(id)
);
create table category(
	id serial primary key,
	category varchar(2000)
);
create table state(
	id serial primary key,
	state varchar(2000)
);
create table item(
	id serial primary key,
	item varchar(2000),
	users_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);
create table comments(
	id serial primary key,
	comment varchar(2000),
	item_id int references item(id)
);
create table attachs(
	id serial primary key,
	attach varchar(2000),
	item_id int references item(id)
);

