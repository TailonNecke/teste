CREATE TABLE pessoa(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    perfil_id bigint,
    primary key (id)
);