
CREATE TABLE IF NOT EXISTS CLIENT(
        id uuid NOT NULL,
        name varchar(300),
        cpf varchar(11) UNIQUE,
        date_of_birth date,
        address_id uuid unique,
        created_at timestamp,
        updated_at timestamp,
        PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ADDRESS(
        id uuid NOT NULL,
        street varchar(256),
        neighborhood varchar(100),
        state varchar(100),
        uf char(2),
        number_of_house integer,
        complement varchar(256),
        cep varchar(9),
        created_at timestamp,
        updated_at timestamp,
        PRIMARY KEY (id)
);

ALTER TABLE CLIENT
ADD CONSTRAINT fk_address_id
FOREIGN KEY (address_id) REFERENCES ADDRESS (id);