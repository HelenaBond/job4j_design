select * from fauna where "name" like '%fish%';
select * from fauna where avg_age between 10000 and 21000;
select * from fauna where discovery_date is null;
select "name" from fauna where discovery_date < '1950-01-01';