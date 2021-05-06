insert into rules(rule) values('no smoke'),('no riding'), ('no coding');
insert into role(role) values ('admin'),('user'),('bot');
insert into users(name,role_id) values ('Egor',1), ('Masha',3);
insert into category(category) values ('middle'), ('junior'), ('senior') ;
insert into state(state) values ('inprogress'), ('done'), ('new');
insert into item(item,users_id,category_id,state_id) values ('bike',1,1,2), ('note',1,2,1), ('pen',2,3,3);
insert into comments(comment,item_id) values ('close your eyes',1),('balagan',1),('sup',2);
insert into attachs(attach,item_id) values ('www.sql.ru',1), ('www.vk.com',3); 