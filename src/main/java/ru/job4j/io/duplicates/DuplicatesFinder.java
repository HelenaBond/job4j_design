package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        printDuple(visitor);
    }

    public static void printDuple(DuplicatesVisitor visitor) {
        visitor.getDupleInfo().forEach(entry -> {
            FileProperty file = entry.getKey();
            System.out.printf("%s, %s%n", file.getName(), file.getSize());
            entry.getValue().forEach(System.out::println);
        });
    }
}
