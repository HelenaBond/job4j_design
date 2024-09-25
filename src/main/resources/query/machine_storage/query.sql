SELECT
    c.id,
    c."name" car_name,
    cb."name" body_name,
    ce."name" engine_name,
    ct."name" transmission_name
FROM cars c
    LEFT JOIN car_bodies cb ON c.body_id = cb.id
    LEFT JOIN car_engines ce ON c.engine_id = ce.id
    LEFT JOIN car_transmissions ct ON c.transmission_id = ct.id;

SELECT
    cb."name" "unused car bodies"
FROM car_bodies cb
    LEFT JOIN cars c ON cb.id = c.body_id
WHERE c.body_id IS NULL;

SELECT
    ce."name" "unused car engine"
FROM car_engines ce
    LEFT JOIN cars c ON ce.id = c.engine_id
WHERE c.engine_id IS NULL;

SELECT
    ct."name" "unuserd car transmission"
FROM car_transmissions ct
    LEFT JOIN cars c ON ct.id = c.transmission_id
WHERE c.transmission_id IS NULL;
