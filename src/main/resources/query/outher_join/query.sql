SELECT * FROM employee e
    LEFT JOIN department d ON e.department_id = d.id;

SELECT * FROM employee e
    RIGHT JOIN department d ON e.department_id = d.id;

SELECT * FROM employee e
    FULL JOIN department d ON e.department_id = d.id;

SELECT d.* FROM department d
    LEFT JOIN employee e ON d.id = e.department_id
WHERE e.department_id IS NULL;

SELECT
    d."name",
    e."name"
FROM department d
    LEFT JOIN employee e ON d.id = e.department_id
WHERE d.id = 1;

SELECT
    d."name",
    e."name"
FROM employee e
    RIGHT JOIN department d ON e.department_id = d.id
WHERE e.department_id = 1;

SELECT
    t1."name" "left name",
    t2."name" "right name"
FROM teens t1
    CROSS JOIN teens t2
WHERE t1.gender <> t2.gender
    AND t1."name" < t2."name";

