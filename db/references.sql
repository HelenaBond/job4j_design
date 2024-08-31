CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    "name" VARCHAR(64),
    email VARCHAR(255) UNIQUE
);
CREATE TABLE passport (
    id SERIAL PRIMARY KEY,
    person_id INTEGER REFERENCES person(id) UNIQUE, -- one to one: владельцем паспорта может быть только один человек
    country VARCHAR(32),
    series_number VARCHAR(32)
);
CREATE TABLE address (
    id SERIAL PRIMARY KEY,
    country VARCHAR(32),
    city VARCHAR(64),
    street VARCHAR(64),
    house SMALLINT,
    info TEXT
);
CREATE TABLE episode (
    id SERIAL PRIMARY KEY,
    "name" VARCHAR(255),
    episode_date DATE,
    address_id INTEGER REFERENCES address(id) -- many to one: несколько событий могут происходить по одному адресу
);

-- many to many: на разные события могут прийти разные люди
CREATE TABLE person_episode (
    person_id INTEGER REFERENCES person(id),
    episode_id INTEGER REFERENCES episode(id),
    PRIMARY KEY (person_id, episode_id)
);
