package ru.job4j.map;

import java.util.*;

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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        User one = new User("Ivan", 10, new GregorianCalendar(1990, 1, 1));
        User two = new User("Ivan", 10, new GregorianCalendar(1990, 1, 1));
        map.put(one, new Object());
        map.put(two, new Object());
        System.out.println(map);
        /**
         * При бобавлении пары ключ-значене в карту высчитывается хэш ключа.
         * По нему высчитывается бакет в котором будет хранится пара.
         * Если бакет занят, то сначала сравниваются хэш-коды ключей.
         * Если хэш-коды равны, то ключи сравниваются с помощью метода equals().
         * Если метод equals() возвращает true,
         * то это означет что ключи идентичны и старое значение которое хранилось под этим ключом
         * будет заменено на новое.
         */
    }
}
