create table products (
    id       serial primary key,
    "name"   varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

--2.1
create or replace function tax_after() returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END
$$ LANGUAGE 'plpgsql';

create trigger product_tax_after
    after insert
    on products
    referencing new table as inserted
    for each statement
    execute procedure tax_after();

--2.2
create or replace function tax_before() returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.2;
        return new;
    END
$$ LANGUAGE 'plpgsql';

create trigger product_tax_before
    before insert
    on products
    for each row
    execute procedure tax_before();
