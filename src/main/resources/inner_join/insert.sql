    INSERT INTO school(address, focus, ownership_type, rating)
    VALUES
    ('Moscow', 'math', null, 3),
    ('Tomsk', 'biology', 'private with license', null),
    ('Kazan', DEFAULT, 'municipal', 10);

    INSERT INTO kid(fullname, birthday, grade, average, school_id)
    VALUES
    ('Petrov Petr Petrovich', '2018-01-25', '7-A math', 95, 1),
    ('Ivanov Ivan Ivanovich', '2017-12-02', '7-B', 80, 1),
    ('Baranov Sergey Sergeevich', '2018-05-15', '7-C', 75, 1),
    ('Nosova Mila Sergeevna', '2018-01-01', '7-A bio', 95, 2),
    ('Abramov Mihail Mihailovich', '2017-10-04', '7-B', 85, 2),
    ('Bloh Ivan Nicolaevich', '2017-10-14', '7-A sport', 100, 3);
