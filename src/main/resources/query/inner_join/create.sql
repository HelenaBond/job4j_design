CREATE TABLE school (
    id SERIAL CONSTRAINT school_id_pk PRIMARY KEY,
    address TEXT NOT NULL,
    focus VARCHAR(255)  DEFAULT 'general',
    ownership_type VARCHAR(255),
    rating SMALLINT
);

CREATE TABLE kid (
    id SERIAL CONSTRAINT kid_id_pk PRIMARY KEY,
    fullname VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    grade VARCHAR(32) NOT NULL,
    average SMALLINT,
    school_id INTEGER CONSTRAINT school_id_fk REFERENCES school(id)
);
