package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    private static final String PATH_NAMES = "data/gc/leak/names.txt";
    private static final String PATH_SURNAMES = "data/gc/leak/surnames.txt";
    private static final String PATH_PATRONS = "data/gc/leak/patr.txt";
    private static final int NEW_USERS = 1000;

    private final List<User> users = new ArrayList<>();
    public List<String> names;
    public List<String> surnames;
    public List<String> patrons;
    private Random random;

    public UserGenerator(Random random) throws IOException {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        if (names != null && surnames != null && patrons != null) {
            for (int i = 0; i < NEW_USERS; i++) {
                String name = "%s %s %s".formatted(
                        (surnames.get(random.nextInt(surnames.size()))),
                        (names.get(random.nextInt(names.size()))),
                        (patrons.get(random.nextInt(patrons.size()))));
                users.add(new User(name));
            }
        }
    }

    private void readAll() throws IOException {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }

    public List<User> getUsers() {
        return users;
    }
}
