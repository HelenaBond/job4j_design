SELECT avg(price) FROM devices;

SELECT
    p."name", AVG(d.price)
FROM people p
    INNER JOIN devices_people dp ON p."id" = dp.people_id
    INNER JOIN devices d ON dp.device_id = d."id"
     GROUP BY p."name"
     ORDER BY p."name";

SELECT
    p."name", AVG(d.price)
FROM people p
    INNER JOIN devices_people dp ON p."id" = dp.people_id
    INNER JOIN devices d ON dp.device_id = d."id"
     GROUP BY p."name"
     HAVING AVG(d.price) > 5000
     ORDER BY p."name";
