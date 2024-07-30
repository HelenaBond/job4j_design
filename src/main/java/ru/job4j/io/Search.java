package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Точка входа ожидает два аргумента запуска: стартовая папка и расширение по которому будут отфильтрованы файлы.
 */
public class Search {
    public static void main(String[] args) throws IOException {
        validArgs(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validArgs(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        if (args[0].isBlank() || args[1].isBlank()) {
            throw new IllegalArgumentException("Arguments can not be empty.");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Not valid extension format. Extension must start with \".\"");
        }
    }
}
