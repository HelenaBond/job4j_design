package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.random.RandomGenerator;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> responses = readPhrases();
        List<String> log = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        String action = CONTINUE;
        while (!action.equals(OUT)) {
            String request = scanner.nextLine();
            log.add(request);
            if (STOP.equals(request)) {
                action = STOP;
            } else if (CONTINUE.equals(request)) {
                action = CONTINUE;
            } else if (OUT.equals(request)) {
                action = OUT;
                log.add("");
                saveLog(log);
            }
            if (action.equals(STOP) || action.equals(OUT)) {
                continue;
            }
            String botResponse = responses.get(new Random().nextInt(responses.size()));
            log.add(botResponse);
            System.out.println(botResponse);
        }
    }

    private List<String> readPhrases() {
        List<String> result = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/dialog.txt", "data/response.txt");
        consoleChat.run();
    }
}
