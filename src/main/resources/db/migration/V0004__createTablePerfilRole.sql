CREATE TABLE role_perfil(
    perfil_id bigint not null,
    role_nome_role varchar(45) not null
);

ALTER TABLE role_perfil ADD CONSTRAINT fk_perfil
FOREIGN KEY (perfil_id) REFERENCES perfil (id);

ALTER TABLE role_perfil ADD CONSTRAINT fk_role
FOREIGN KEY (role_nome_role) REFERENCES role (nome_role);