package ru.job4j.cache.dirfile;

import ru.job4j.cache.AbstractCache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    public String getCachingDir() {
        return cachingDir;
    }

    @Override
    protected String load(String fileName) {
        String result = null;
        Path path = Path.of(cachingDir, fileName);
        if (!Files.exists(path)) {
            System.out.printf("Файл по пути \"%s\" не существует.%n", path);
        }
        try {
            result = Files.readString(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        put(path.toString(), result);
        return result;
    }
}
