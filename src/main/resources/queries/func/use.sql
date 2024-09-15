insert into
    products_pf (name, producer, count, price)
values
    ('product_2', 'producer_2', 15, 32),
    ('product_1', 'producer_1', 3, 50),
    ('product_3', 'producer_3', 8, 115);

select delete_products_by_price(50);
call delete_products_less_than(8);
