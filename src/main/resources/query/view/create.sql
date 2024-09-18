create table students
(
    id   serial primary key,
    "name" varchar(50)
);

create table authors
(
    id   serial primary key,
    "name" varchar(50)
);

create table books
(
    id serial primary key,
    "name" varchar(200),
    author_id integer references authors (id)
);

create table orders
(
    id serial primary key,
    active boolean default true,
    book_id integer references books (id),
    student_id integer references students (id)
);
