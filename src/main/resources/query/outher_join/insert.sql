INSERT INTO
    department("name")
VALUES
    ('HR'),
    ('Accounting'),
    ('Engineering');

INSERT INTO
    employee("name", department_id)
VALUES
    ('Bob', 1),
    ('Jon', null),
    ('Max', 1),
    ('Petr', 2),
    ('Ivan', null),
    ('Tom', 2);

INSERT INTO
    teens("name", gender)
VALUES
    ('Max', 'men'),
    ('Lena', 'woman'),
    ('Ivan', 'cat'),
    ('Bob', 'transgender woman'),
    ('Ann', 'transgender man'),
    ('Kate', 'woman');
