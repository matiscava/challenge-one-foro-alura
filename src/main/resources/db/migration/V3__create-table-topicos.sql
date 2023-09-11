create table if not exists topicos(
    id bigint not null primary key auto_increment,
    titulo varchar(50) not null ,
    mensaje varchar(250) not null ,
    fecha_creacion datetime not null ,
    status varchar(100) not null ,
    autor_id bigint not null ,
    curso_id bigint not null ,

    CONSTRAINT fk_topicos_autor_id foreign key (autor_id) references usuarios(id),
    CONSTRAINT fk_topicos_curso_id foreign key (curso_id) references cursos(id)
)