CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

CREATE TABLE customers_orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO
    customers(first_name, last_name, "age", country)
VALUES
    ('Петр', 'Петров', 10, 'Россия'),
    ('Иван', 'Иванов', 20, 'Россия'),
    ('Сергей', 'Сергеев', 30, 'Россия'),
    ('Дмитрий', 'Печкин', 40, 'Украина');

INSERT INTO
    customers_orders(amount, customer_id)
VALUES
    (1000, 1),
    (2000, 2),
    (3000, 2);

SELECT * FROM
    customers c
WHERE "age" = (SELECT MIN("age") FROM customers);

SELECT * FROM
    customers c
WHERE c.id NOT IN (SELECT customer_id c_id FROM customers_orders);
