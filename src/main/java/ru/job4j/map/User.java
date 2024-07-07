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
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        User one = new User("Ivan", 10, new GregorianCalendar(1990, 1, 1));
        User two = new User("Ivan", 10, new GregorianCalendar(1990, 1, 1));
        map.put(one, new Object());
        map.put(two, new Object());
        System.out.println(one.hashCode() == two.hashCode());
        System.out.println(map);
        /**
         * 6.1 Пары попали в один бакет.
         * 6.2 Проверка ключей на равенство хэш-кодов проводилась. Потому что пары попали в один бакет.
         * 6.3 Проверка ключей на равенство через метод equals() тоже проводилась,
         * потому что их хэш-коды равны.
         * Т.к метод equals() не переопределён, то для ключей one и two он вернул false.
         * В итоге в карте занят только один бакет в котором лежит Entry с первой добавленной парой,
         * у которой есть ссылка на Entry со второй добавленной парой.
         */
    }
}
