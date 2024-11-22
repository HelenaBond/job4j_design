package ru.job4j.cache.menu;

import ru.job4j.cache.dirfile.DirFileCache;
import ru.job4j.cache.dirfile.ValidDirFileSources;

import java.util.Scanner;

/**
 * каждый раз при вводе новой директории пересоздаётся кэш.
 */
public class Emulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ValidDirFileSources validator = new ValidDirFileSources();
        DirFileCache cache = null;
        boolean running = true;

        while (running) {
            System.out.println("1. Указать кэшируемую директорию");
            System.out.println("2. Загрузить содержимое файла в кэш");
            System.out.println("3. Получить содержимое файла из кэша");
            System.out.println("4. Выход");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите путь к кэшируемой директории: ");
                    String dir = scanner.nextLine();
                    if (dir.isBlank()) {
                        System.out.println("Директория не может быть пуста.");
                    }
                    if (cache == null || !cache.getCachingDir().equals(dir)) {
                        validator.validSource(dir);
                        cache = new DirFileCache(dir);
                    }
                }
                case 2 -> {
                    System.out.print("Введите имя файла для загрузки в кэш: ");
                    String fileToLoad = scanner.nextLine();
                    if (fileToLoad.isBlank()) {
                        System.out.println("Имя файла не может быть пустым.");
                    }
                    if (cache != null) {
                        if (validator.validPathFile(cache.getCachingDir(), fileToLoad)) {
                            cache.get(fileToLoad);
                            System.out.println("Файл загружен в кэш.");
                        }
                    } else {
                        System.out.println("Сначала укажите кэшируемую директорию.");
                    }
                }
                case 3 -> {
                    System.out.print("Введите имя файла для получения из кэша: ");
                    String fileToGet = scanner.nextLine();
                    if (fileToGet.isBlank()) {
                        System.out.println("Имя файла не может быть пустым.");
                    }
                    if (cache != null) {
                        String content = cache.get(fileToGet);
                        if (content != null) {
                            System.out.println("Содержимое файла: " + content);
                        }
                    } else {
                        System.out.println("Сначала укажите кэшируемую директорию.");
                    }
                }
                case 4 -> {
                    running = false;
                }
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
}
