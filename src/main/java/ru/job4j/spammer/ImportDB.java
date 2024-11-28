package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private final Properties config;
    private final String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(p -> {
                String[] cols = p.split(";");
                validate(cols, p);
                users.add(new User(cols[0], cols[1]));
            });
        }
        return users;
    }

    private void validate(String[] cols, String string) {
        if (cols.length != 2
                || cols[0].isBlank()
                || cols[1].isBlank()) {
            throw new IllegalArgumentException(
                    String.format("Строка %s не соответствует шаблону \"имя;почта;\"", string));
        }
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("connection.driver_class"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("connection.url"),
                config.getProperty("connection.login"),
                config.getProperty("connection.password")
        )) {
            for (User user : users) {
                String sql = "INSERT INTO users(name, email) values (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "./data/dump.txt");
        dataBase.save(dataBase.load());
    }
}
