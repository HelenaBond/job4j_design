package ru.job4j.cache.dirfile;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class ValidDirFileSources {
    public void validSource(String dir) {
        try {
            if (!Files.isDirectory(Path.of(dir))) {
                System.out.printf("Путь %s не директория.%n", dir);
            }
        } catch (InvalidPathException | SecurityException ipe) {
            System.out.println(ipe.getMessage());
        }
    }

    public boolean validPathFile(String dir, String fileName) {
        boolean valid = true;
        try {
            Path result = Path.of(dir, fileName);
            if (Files.isDirectory(result)) {
                System.out.printf("Путь \"%s\" это директория. "
                        + "Если вам нужны файлы из этой директории - введите в пункт 1 директорию полностью.%n",
                        result);
                valid = false;
            }
            if (!Files.exists(result)) {
                System.out.printf("Файл по пути \"%s\" не существует.%n", result);
                valid = false;
            }
        } catch (InvalidPathException | SecurityException ipe) {
            System.out.println(ipe.getMessage());
            valid = false;
        }
        return valid;
    }
}
