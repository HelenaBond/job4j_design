INSERT INTO
    devices("name", price)
VALUES
    ('phone', 4000),
    ('iphone', 200000),
    ('washing machine', 40000),
    ('fridge', 100000);

INSERT INTO
    people("name")
VALUES
    ('Ivan'),
    ('Petr'),
    ('Oleg'),
    ('Sergey'),

INSERT INTO
    devices_people(device_id, people_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 2),
    (3, 3),
    (4, 4);
