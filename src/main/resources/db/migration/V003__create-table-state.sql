CREATE TABLE state
(
    id   BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(80) NOT NULL
) engine=InnoDB DEFAULT charset=utf8;

##
VAI PEGAR OS DADOS QUE JA ESTAO NA TABELA DE CIDADE E VAI PASSAR PARA A TABELA DE ESTADO
INSERT INTO state (name)
SELECT DISTINCT state_name
FROM city;

ALTER TABLE city
    ADD COLUMN state_id BIGINT NOT NULL;

UPDATE city c
SET c.state_id =
            (SELECT s.id FROM state s WHERE s.name = c.state_name);

ALTER TABLE city
    ADD CONSTRAINT fk_city_state
        FOREIGN KEY (state_id) REFERENCES state (id);

ALTER TABLE city DROP COLUMN state_name;

ALTER TABLE city CHANGE city_name name VARCHAR (80) NOT NULL;