package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {
    @Test
    public void whenTwoPeriods(@TempDir Path tempDir) {
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("server2.log").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("300 10:59:01");
            output.println("500 11:01:02");
            output.println("200 11:02:02");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File target  = tempDir.resolve("target2.csv").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            assertThat(reader.readLine()).isEqualTo("10:57:01;10:59:01;");
            assertThat(reader.readLine()).isEqualTo("11:01:02;11:02:02;");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenManyErrorsInOnePeriod(@TempDir Path tempDir) {
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("server3.log").toFile();
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("500 10:59:01");
            writer.println("400 11:01:02");
            writer.println("300 11:02:02");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File target = tempDir.resolve("target3.csv").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            assertThat(reader.readLine()).isEqualTo("10:57:01;11:02:02;");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
