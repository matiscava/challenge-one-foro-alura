create table if not exists respuestas(
    id bigint not null auto_increment primary key ,
    mensaje varchar(200) not null ,
    topico_id bigint not null ,
    fechaCreacion datetime not null ,
    autor_id bigint not null ,
    solucion tinyint not null,

    CONSTRAINT fk_respuestas_topico_id foreign key (topico_id) references topicos(id),
    CONSTRAINT fk_respuestas_autor_id foreign key (autor_id) references usuarios(id)
)