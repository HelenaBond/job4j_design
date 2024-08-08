package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Класс ожидает, что если разделитель является частью значения из ячейки,
 * то такое значение заключено в двойные ковычки ".
 * Если ваше значение начинается с ковычек например "n" n; то заключите всё значение в ещё одни ковычки ""n" n";
 * Если фильтрация не нужна, то в значении флага -filter укажите все поля.
 */
public class CSVReader {

    public static final String PATH = "path";
    public static final String DELIMITER = "delimiter";
    public static final String OUT = "out";
    public static final String FILTER = "filter";

    public static void handle(ArgsName argsName) throws IOException {
        List<String> filterArgs = new LinkedList<>();
        var scanner = new Scanner(new CharArrayReader(argsName.get(FILTER).toCharArray())).useDelimiter(",");
        while (scanner.hasNext()) {
            filterArgs.add(scanner.next().trim());
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
            if (temp.startsWith("\"")) {
                while (!temp.endsWith("\"") && scanner.hasNext()) {
                    String next = scanner.next();
                    temp = next.trim();
                    builder.append(delimiter);
                    builder.append(next);
                }
            }
            result.add(String.valueOf(builder));
        }
        return result;
    }

    public static void validArgsToReader(ArgsName argsName) throws IOException {
        String input = argsName.get("path");
        if (input == null) {
            throw new IllegalArgumentException("'path' argument is required.");
        }
        Path inputPath = Path.of(input);
        if (!Files.exists(inputPath)) {
            throw new IllegalArgumentException(String.format("The path \"%s\" does not exist.", inputPath));
        }
        if (Files.isDirectory(inputPath)) {
            throw new IllegalArgumentException(String.format("The path \"%s\" is a directory.", inputPath));
        }

        if (argsName.get("delimiter") == null) {
            throw new IllegalArgumentException("'delimiter' argument is required.");
        }

        String filter = argsName.get("filter");
        if (filter == null) {
            throw new IllegalArgumentException("'filter' argument is required.");
        }
        if (filter.isBlank()) {
            throw new IllegalArgumentException("'filter' can not be empty.");
        }

        String out = argsName.get("out");
        if (out == null) {
            throw new IllegalArgumentException("'out' argument is required.");
        }
        if (!out.equals("stdout")) {
            try {
                Files.createFile(Path.of(out));
            } catch (IOException e) {
                throw new IOException(
                        String.format("'out' argument should be 'stdout' or valid path to this OC. %s", e.getMessage()));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validArgsToReader(argsName);
        handle(argsName);
    }
}
