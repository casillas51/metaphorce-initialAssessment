insert into role (role) values ('ADMIN');
insert into role (role) values ('USER');

insert ignore into user (username, password, id_role) values ('Admin', 'Adm123', 1);
insert ignore into user (username, password, id_role) values ('User', 'User123', 2);