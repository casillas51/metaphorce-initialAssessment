insert ignore into roles (role) values ('ADMIN');
insert ignore into roles (role) values ('USER');

insert ignore into users (username, password, id_role) values ('Admin', 'Adm123', 1);
insert ignore into users (username, password, id_role) values ('User', 'User123', 2);