CREATE VIEW students_active_orders AS
SELECT
    s.name student_name,
    a.name author_name,
    b.name book_name,
    o.active order_active
FROM
    students s
JOIN
    orders o ON s.id = o.student_id
JOIN
    books b ON o.book_id = b.id
JOIN
    authors a ON b.author_id = a.id
ORDER BY
    s.name, a.name, b.name;
