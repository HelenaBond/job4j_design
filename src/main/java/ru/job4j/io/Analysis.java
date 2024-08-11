package ru.job4j.io;

import java.io.*;

public class Analysis {
    /**
     * Данные в файле source должны быть отсортированными. Без лишних пробелов, комментариев, пустых строк.
     */
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
            PrintWriter writer = new PrintWriter(target)) {
            boolean isError = false;
            String nextLine = reader.readLine();
            StringBuilder period = new StringBuilder();
            while (nextLine != null) {
                String[] lineParts = nextLine.split(" ", 2);
                int status = Integer.parseInt(lineParts[0]);
                if (!isError && status >= 400 && status <= 500) {
                    period.append(lineParts[1]);
                    period.append(";");
                    isError = true;
                } else if (isError && status >= 200 && status < 400) {
                    period.append(lineParts[1]);
                    period.append(";");
                    writer.println(period);
                    period = new StringBuilder();
                    isError = false;
                }
                nextLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
