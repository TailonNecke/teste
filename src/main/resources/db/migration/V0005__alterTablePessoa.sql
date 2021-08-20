ALTER TABLE pessoa ADD CONSTRAINT fk_perfil_pessoa
FOREIGN KEY (perfil_id) REFERENCES perfil (id);