SELECT
    p.*
FROM product p
    INNER JOIN "type" t ON p.type_id = t.id
WHERE t."name" = 'СЫР';

SELECT * FROM product p
WHERE p."name" LIKE '%мороженое%';

SELECT * FROM product p
WHERE p.expired_date < CURRENT_DATE;

SELECT * FROM product p
WHERE p.price = (
    SELECT MAX(price) FROM product
);

SELECT
    t."name" "type name",
    COUNT(p.type_id) products
FROM "type" t
    INNER JOIN product p ON t.id = p.type_id
GROUP BY t.id;

SELECT
    p.*
FROM product p
    INNER JOIN "type" t ON p.type_id = t.id
WHERE t."name" = 'СЫР' OR t."name" = 'МОЛОКО';

SELECT
    t."name" "type name",
    COUNT(p.type_id) products
FROM "type" t
    INNER JOIN product p ON t.id = p.type_id
GROUP BY t.id
HAVING COUNT(p.type_id) < 10;

SELECT
    p."name" "product name",
    p.expired_date "expired date",
    p.price, t."name" "type name"
FROM product p
    INNER JOIN "type" t ON p.type_id = t.id;
