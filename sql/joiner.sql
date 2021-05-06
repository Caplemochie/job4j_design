create table authors(
    id serial primary key,
    name varchar(255)
);

create table books(
    id serial primary key,
    name varchar(255),
    book_id int references authors(id)
);

insert into authors(name) values ('Dostaevskii');
insert into authors(name) values ('Pushkin');
insert into authors(name) values ('Tagir');
insert into authors(name) values ('Momo');

insert into books(name, book_id) values ('Mandalorec', 3);
insert into books(name, book_id) values ('Crime and Punishment', 1);
insert into books(name, book_id) values ('Krivoi zub', 3);
insert into books(name, book_id) values ('Butcher', 4);

select * from authors a join books b on b.book_id = a.id;
select a.name, b.name from authors a join books b on b.book_id = a.id;
select * from authors a join books b on b.book_id = a.id where book_id in (1,2,3);