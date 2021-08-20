CREATE TABLE produto(
    id bigint not null auto_increment,
    produto varchar(100) not null,
    quantidade bigint,
    valor_unitario DOUBLE,
    valor_total_em_estoque DOUBLE,
    primary key (id)
);