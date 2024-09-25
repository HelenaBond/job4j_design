    insert into rules(rule)
    values
    ('read'),
    ('create'),
    ('update'),
    ('delete');

    insert into roles(role)
    values
    ('manager'),
    ('admin'),
    ('techup');

    insert into roles_rules(role_id, rule_id)
    values
    (1, 1),
    (1, 3),
    (2, 1),
    (2, 2),
    (2, 3),
    (3, 1),
    (3, 2),
    (3, 3),
    (3, 4);

    insert into users(username, email, role_id)
    values
    ('Продавцов', 'prod@mail.com', 1),
    ('Админов', 'admin@mail.com', 2),
    ('Суппортов', 'tech@mail.com', 3);

    insert into categories(category)
    values
    ('Инцидент'),
    ('Запрос данных'),
    ('Отчет');

    insert into states(state)
    values
    ('Создана'),
    ('В работе'),
    ('Выполнение приостановлено'),
    ('Выполнено');

    insert into items(item_name, description, created, item_performer_id, category_id, state_id)
    values
    ('Отгрузка яблок', 'Отгрузка яблок на приграничный склад', '2024-08-17 07:37:16', 1, 2, 1),
    ('Статистика по отгрузкам', 'Статистика по отгрузкам яблок за неделю', '2024-08-17 08:37:16', 2, 3, 3),
    ('Не читаемый формат договора поставщика',
     'Настроить формат для поставщика яблок',
     '2024-08-17 09:37:16', 3, 1, 4);

     insert into comments(comment, item_id, comment_author_id)
     values
     ('Чтобы продолжить работу нужен читаемый договор', 2, 2);

     insert into attachs(file_name, file_type, file_size, file_data, item_id)
     values
     ('Договор на поставку яблок', 'pdf', '234567', 'Текст договора', 3);
