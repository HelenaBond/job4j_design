package ru.job4j.map;

import java.util.*;
import java.util.random.RandomGenerator;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return RandomGenerator.getDefault().hashCode();
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        User one = new User("Ivan", 10, new GregorianCalendar(1990, 1, 1));
        User two = new User("Ivan", 10, new GregorianCalendar(1990, 1, 1));
        map.put(one, new Object());
        map.put(two, new Object());
        System.out.println(map);
        /**
         * 5.1 Пары попали в разные бакеты. Потому что у ключей разные хеш-коды.
         * 5.2 Проверка ключей на равенство их хеш кодов не проводилась. Потому что ключи попали в разные бакеты.
         * 5.3 Прооверка на equals() не проводилась. Потому что ключи попали в разные бакеты.
         */
    }
}
