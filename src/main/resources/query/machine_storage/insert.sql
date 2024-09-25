INSERT INTO
    car_bodies("name")
VALUES
    ('седан'),
    ('хэтчбек'),
    ('пикап');

INSERT INTO
    car_engines("name")
VALUES
    ('бензиновый'),
    ('дизельный'),
    ('электродвигатель');

INSERT INTO
    car_transmissions("name")
VALUES
    ('механика'),
    ('автоматика'),
    ('вариатор');

INSERT INTO
    cars("name", body_id, engine_id, transmission_id)
VALUES
    ('Toyota Camry', null, 1, 1),
    ('Honda Accord', null, 1, 1),
    ('Volkswagen Golf TDI', 2, null, 2),
    ('Ford Focus Diesel', 2, null, 2),
    ('Rivian R1T', 3, 3, null),
    ('Ford F-150 Lightning', 3, 3, null);
