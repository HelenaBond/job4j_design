--1
select "name" from movie
INTERSECT
select title from book;
--2
select title from book
except
select "name" from movie;
--3
select "name" from movie
union
select title from book
except
(select "name" from movie
intersect
select title from book);
