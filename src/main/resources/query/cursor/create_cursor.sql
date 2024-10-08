create table products_c
(
    id    serial primary key,
    "name"  varchar(50),
    "count" integer default 0,
    price integer
);

insert into
    products_c (name, count, price)
VALUES
    ('product_1', 1, 5),
    ('product_2', 2, 10),
    ('product_3', 3, 15),
    ('product_4', 4, 20),
    ('product_5', 5, 25),
    ('product_6', 6, 30),
    ('product_7', 7, 35),
    ('product_8', 8, 40),
    ('product_9', 9, 45),
    ('product_10', 10, 50),
    ('product_11', 11, 55),
    ('product_12', 12, 60),
    ('product_13', 13, 65),
    ('product_14', 14, 70),
    ('product_15', 15, 75),
    ('product_16', 16, 80),
    ('product_17', 17, 85),
    ('product_18', 18, 90),
    ('product_19', 19, 95),
    ('product_20', 20, 100);

BEGIN;
DECLARE
    reverse_cursor cursor for
select * from products_c;

FETCH LAST FROM reverse_cursor;

MOVE BACKWARD 6 FROM reverse_cursor;
FETCH NEXT FROM reverse_cursor;

MOVE BACKWARD 9 FROM reverse_cursor;
FETCH NEXT FROM reverse_cursor;

MOVE BACKWARD 6 FROM reverse_cursor;
FETCH NEXT FROM reverse_cursor;

FETCH FIRST FROM reverse_cursor;

CLOSE reverse_cursor;
COMMIT;

