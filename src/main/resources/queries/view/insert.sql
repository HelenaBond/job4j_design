insert into
    students ("name")
values
    ('Иван Иванов'),
    ('Петр Петров');

insert into
    authors ("name")
values
    ('Александр Пушкин'),
    ('Николай Гоголь');

insert into
    books ("name", author_id)
values
    ('Евгений Онегин', 1),
    ('Капитанская дочка', 1),
    ('Дубровский', 1),
    ('Мертвые души', 2),
    ('Вий', 2);

insert into orders (book_id, student_id)
values
    (1, 1),
    (3, 1),
    (5, 2),
    (4, 1),
    (2, 2);
