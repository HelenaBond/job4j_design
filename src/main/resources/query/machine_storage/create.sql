CREATE TABLE car_bodies (
    id SERIAL CONSTRAINT car_bodies_pkey PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL CONSTRAINT car_bodies_name_key UNIQUE
);

CREATE TABLE car_engines (
    id SERIAL CONSTRAINT car_engines_pkey PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL CONSTRAINT car_engines_name_key UNIQUE
);

CREATE TABLE car_transmissions(
    id SERIAL CONSTRAINT car_transmissions_pkey PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL CONSTRAINT car_transmissions_name_key UNIQUE
);

CREATE TABLE cars (
    id SERIAL CONSTRAINT cars_pkey PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL CONSTRAINT cars_name_key UNIQUE,
    body_id INTEGER CONSTRAINT cars_body_id_fkey REFERENCES car_bodies(id),
    engine_id INTEGER CONSTRAINT cars_engine_id_fkey REFERENCES car_engines(id),
    transmission_id INTEGER CONSTRAINT cars_transmission_id_fkey REFERENCES car_transmissions(id)
);
