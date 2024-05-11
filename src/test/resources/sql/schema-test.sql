create sequence usuario_seq start with 1 increment by 50;

create table usuario (
    id_usuario bigint not null auto_increment,
    contrasenia varchar(255) not null,
    nombre_usuario varchar(255) not null unique,
    rol varchar(255) not null,
    primary key (id_usuario)
);