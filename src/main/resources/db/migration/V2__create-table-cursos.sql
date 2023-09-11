create table if not exists cursos (
    id bigint not null auto_increment primary key ,
    nombre varchar(100) not null ,
    categoria varchar(100) not null
)