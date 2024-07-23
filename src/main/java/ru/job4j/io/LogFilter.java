package ru.job4j.io;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> filtered = Collections.emptyList();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            filtered = input.lines()
                    .filter(str -> {
                        String[] all = str.split(" ");
                        return "404".equals(all[all.length - 2]);
                    })
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filtered;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}
