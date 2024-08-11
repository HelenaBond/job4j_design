package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Точка входа ожидает два аргумента запуска: стартовая папка и расширение по которому будут отфильтрованы файлы.
 */
public class Search {
    public static void main(String[] args) throws IOException {
        String[] stripArgs = Arrays.stream(args).map(String::strip).toArray(String[]::new);
        validArgs(stripArgs);
        Path start = Paths.get(stripArgs[0]);
        String extension = stripArgs[1];
        search(start, path -> path.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validArgs(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Insufficient arguments. Usage: ROOT_FOLDER EXTENSION.");
        }
        String start = args[0];
        String extension = args[1];
        if (start.isEmpty() || extension.isEmpty()) {
            throw new IllegalArgumentException("Arguments can not be empty.");
        }
        Path startFolder = Paths.get(start);
        if (!Files.exists(startFolder)) {
            throw new IllegalArgumentException(String.format("The path \"%s\" does not exist.", startFolder));
        }
        if (Files.isDirectory(startFolder)) {
            throw new IllegalArgumentException(String.format("The path \"%s\" exists but is a directory.", startFolder));
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Not valid extension format. Extension must start with \".\"");
        }
    }
}
