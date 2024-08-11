package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;

/**
 * Класс ожидает, что если разделитель является частью значения из ячейки,
 * то такое значение заключено в двойной тэг << . . . >>.
 * Не используйте двойной тэг внутри значений.
 * Если фильтрация не нужна, то в значении флага -filter укажите все поля.
 * Если какое либо из значений отсутствует, то на его место поставить пробел.
 */
public class CSVReader {

    public static final String PATH = "path";
    public static final String DELIMITER = "delimiter";
    public static final String OUT = "out";
    public static final String FILTER = "filter";

    public static void handle(ArgsName argsName) throws IOException {
        List<String> filterArgs = new LinkedList<>();
        var scanner = new Scanner(new CharArrayReader(argsName.get(FILTER).toCharArray())).useDelimiter("[ *, *]");
        while (scanner.hasNext()) {
            filterArgs.add(scanner.next());
        }
        List<String> allRows = new LinkedList<>();
        try (var reader = new Scanner(new BufferedReader(new FileReader(argsName.get(PATH))))) {
            while (reader.hasNext()) {
                allRows.add(reader.nextLine());
            }
        }
        List<String> filteredRows = filter(allRows, filterArgs, argsName.get(DELIMITER));
        Files.write(Path.of(argsName.get(OUT)), filteredRows);
    }

    private static List<String> filter(List<String> allRows, List<String> filterArgs, String delimiter) {
        List<String> columnsName = parseString(allRows.get(0), delimiter);
        List<Integer> indexes = findColumns(columnsName, filterArgs);
        List<String> filteredRows = new LinkedList<>();
        for (String row : allRows) {
            List<String> parseRow = parseString(row, delimiter);
            StringJoiner current = new StringJoiner(delimiter);
            for (int index : indexes) {
                current.add(parseRow.get(index));
            }
            filteredRows.add(String.valueOf(current));
        }
        return filteredRows;
    }

    private static List<Integer> findColumns(List<String> columnsName, List<String> filterArgs) {
        List<Integer> indexes = new LinkedList<>();
        for (String filter : filterArgs) {
            int i = columnsName.indexOf(filter);
            if (i != -1) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    private static List<String> parseString(String row, String delimiter) {
        List<String> result = new ArrayList<>();
        var scanner = new Scanner(new CharArrayReader(row.toCharArray())).useDelimiter(delimiter);
        while (scanner.hasNext()) {
            String current = scanner.next();
            StringBuilder builder = new StringBuilder(current);
            String temp = current.trim();
            if (!temp.isEmpty()) {
                if (temp.startsWith("<<")) {
                    while (!temp.endsWith(">>") && scanner.hasNext()) {
                        String next = scanner.next();
                        temp = next.trim();
                        builder.append(delimiter);
                        builder.append(next);
                    }
                }
            }
            result.add(String.valueOf(builder));
        }
        return result;
    }

    public static void validArgsToReader(ArgsName argsName) {
        String input = argsName.get(PATH);
        if (input == null) {
            throw new IllegalArgumentException(String.format("%s argument is required.", PATH));
        }
        Path inputPath = Path.of(input);
        if (!Files.exists(inputPath)) {
            throw new IllegalArgumentException(String.format("The path \"%s\" does not exist.", inputPath));
        }
        if (Files.isDirectory(inputPath)) {
            throw new IllegalArgumentException(String.format("The path \"%s\" is a directory.", inputPath));
        }

        if (argsName.get(DELIMITER) == null) {
            throw new IllegalArgumentException(String.format("%s argument is required.", DELIMITER));
        }

        String filter = argsName.get(FILTER);
        if (filter == null) {
            throw new IllegalArgumentException(String.format("%s argument is required.", FILTER));
        }
        if (filter.isBlank()) {
            throw new IllegalArgumentException(String.format("%s can not be empty.", FILTER));
        }

        String out = argsName.get(OUT);
        if (out == null) {
            throw new IllegalArgumentException(String.format("%s argument is required.", OUT));
        }
        if (!out.equals("stdout")) {
            try {
                Path.of(out);
            } catch (InvalidPathException e) {
                throw new InvalidPathException(
                        String.format("%s argument should be 'stdout' or valid path to this OC. ", OUT), e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validArgsToReader(argsName);
        handle(argsName);
    }
}
