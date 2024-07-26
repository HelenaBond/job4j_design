package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {
    @Test
    public void whenTwoPeriods() {
        Analysis analysis = new Analysis();
        String target = "data/target2.csv";
        analysis.unavailable("data/server2.log", target);
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            assertThat(reader.readLine()).isEqualTo("10:57:01;10:59:01;");
            assertThat(reader.readLine()).isEqualTo("11:01:02;11:02:02;");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenManyErrorsInOnePeriod() {
        Analysis analysis = new Analysis();
        String target = "data/target3.csv";
        analysis.unavailable("data/server3.log", target);
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            assertThat(reader.readLine()).isEqualTo("10:57:01;11:02:02;");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}