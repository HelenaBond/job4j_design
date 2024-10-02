CREATE TABLE person(
    id serial PRIMARY KEY,
    person_name VARCHAR(50),
    gender boolean,
    birthday date
);

INSERT INTO person(person_name, gender, birthday)
            VALUES ('Ivan', TRUE, 1990-02-15);

UPDATE person SET person_name = 'Bob';

DELETE FROM person;
