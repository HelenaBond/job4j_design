create table product_s (
    id       serial primary key,
    "name"   varchar(50),
    producer varchar(50),
    "count"  integer default 0,
    price    integer
);

insert into
    product_s ("name", producer, "count", price)
values
    ('product_1', 'producer_1', 3, 50),
    ('product_2', 'producer_2', 15, 32),
    ('product_3', 'producer_3', 8, 115);

begin transaction;
insert into product_s ("name", producer, "count", price) values ('product_5', 'producer_5', 111, 640);
commit transaction;

select * from product_s;

begin transaction;
delete from product_s;
drop table product_s;
rollback transaction;

select * from product_s;

begin transaction;
insert into product_s ("name", producer, "count", price) values ('product_6', 'producer_6', 17, 45);
savepoint first_savepoint;
delete from product_s where price = 115;
update product_s set price = 75 where "name" = 'product_1';
select * from product_s;
rollback to first_savepoint;
select * from product_s;
commit transaction;
