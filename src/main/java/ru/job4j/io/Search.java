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
        String extension = args[1];
        search(start, path -> path.toFile().getName().endsWith(extension)).forEach(System.out::println);
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
        String start = args[0];
        String extension = args[1];
        if (start.isBlank() || extension.isBlank()) {
            throw new IllegalArgumentException("Arguments can not be empty.");
        }
        Path startFolder = Paths.get(start);
        if (!Files.isDirectory(startFolder) && !Files.exists(startFolder)) {
            throw new IllegalArgumentException(String.format("The file in directory \"%s\" not exist.", startFolder));

        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Not valid extension format. Extension must start with \".\"");
        }
    }
}
