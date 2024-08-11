package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return value;
    }

    private void parse(String[] args) {
        for (String pair : args) {
            pair = pair.strip();
            int delimiter = pair.indexOf("=");
            validArg(pair, delimiter);
            values.put(pair.substring(1, delimiter).stripTrailing(),
                    pair.substring(delimiter + 1).stripLeading());
        }
    }

    private void validArg(String arg, int delimiter) {
        if (arg.charAt(0) != '-') {
            throw new IllegalArgumentException(
                    String.format("Error: This argument '%s' does not start with a '-' character", arg));

        }
        if (delimiter == 1) {
            throw new IllegalArgumentException(
                    String.format("Error: This argument '%s' does not contain a key", arg));
        }
        if (delimiter == arg.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("Error: This argument '%s' does not contain a value", arg));
        }
        if (delimiter == -1) {
            throw new IllegalArgumentException(
                    String.format("Error: This argument '%s' does not contain an equal sign", arg));
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
