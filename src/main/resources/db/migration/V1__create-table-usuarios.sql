create table if not exists usuarios(
    id bigint not null auto_increment primary key ,
    nombre varchar(50) not null ,
    email varchar(100) not null ,
    contrasena varchar(300) not null
)