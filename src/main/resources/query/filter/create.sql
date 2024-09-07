CREATE TABLE type(
    id SERIAL CONSTRAINT type_pk PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE product (
    id SERIAL CONSTRAINT product_pk PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL,
    type_id INTEGER NOT NULL CONSTRAINT type_fk REFERENCES type(id),
    expired_date DATE NOT NULL,
    price NUMERIC(21, 2) NOT NULL
);