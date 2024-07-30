package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Stream;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> duple = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
            FileProperty key = new FileProperty(file.getFileName().toString(), attributes.size());
            List<Path> paths = duple.getOrDefault(key, new LinkedList<>());
            paths.add(file);
            duple.put(key, paths);
        return super.visitFile(file, attributes);
    }

    public Stream<Map.Entry<FileProperty, List<Path>>> getDupleInfo() {
        return duple.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1);
    }
}
