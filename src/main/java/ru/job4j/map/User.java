package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        User one = new User("Ivan", 10, new GregorianCalendar(1990, 1, 1));
        User two = new User("Ivan", 10, new GregorianCalendar(1990, 1, 1));
        map.put(one, new Object());
        map.put(two, new Object());
        System.out.println(map);
        /**
         * 5.1 Пары попали в разные бакеты.
         * 5.2 Проверка ключей на равенство их хеш кодов проводилась.
         * Была использована реализация по умолчанию из класса Object
         * (неявного родителя всех классов, в том числе и User)
         * которая возвращает уникалный hash для каждого объекта основанный на адресе в памяти,
         * а не на ключевых свойствах объекта.
         * 5.3 Прооверка на equals() не проводилась.
         * Потому что ключи попали в разные бакеты,
         * а ключи из разных бакетов не сравниваются друг с другом,
         * потому что между ними нет коллизии.
         */
    }
}
