create table products_pf (
    id       serial primary key,
    "name"   varchar(50),
    producer varchar(50),
    "count"  integer default 0,
    price    integer
);

CREATE
OR REPLACE FUNCTION delete_products_by_price(price_limit integer)
RETURNS integer
AS $$
    DECLARE
        rows_deleted integer;
    BEGIN
        DELETE FROM products_pf
        WHERE price <= price_limit;
        GET DIAGNOSTICS rows_deleted = ROW_COUNT;
        RETURN rows_deleted;
        END;
$$ LANGUAGE plpgsql;

CREATE
OR REPLACE PROCEDURE delete_products_less_than(quantity_threshold integer)
AS $$
    BEGIN
        DELETE FROM products_pf
        WHERE "count" <= quantity_threshold;
    END;
$$ LANGUAGE plpgsql;