package ru.job4j.ood.lsp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Food;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CalculatePercentExpiryTest {

    public static LocalDate now;

    @BeforeAll
    public static void init() {
        now = LocalDate.of(2024, 12, 12);
    }

    @Test
    public void  when12And12Then0Percent() {
        Food product = new Food(
                "milk",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 12),
                100);
        Calculate calculate = new CalculatePercentExpiry();
        assertThat(calculate.percentFresh(product, now)).isCloseTo(99.0, within(0.1));
    }

    @Test
    public void  when11And12Then50Percent() {
        Food product = new Food(
                "milk",
                LocalDate.of(2024, 12, 11),
                LocalDate.of(2024, 12, 12),
                100);
        Calculate calculate = new CalculatePercentExpiry();
        assertThat(calculate.percentFresh(product, now)).isCloseTo(50.0, within(0.1));
    }

    @Test
    public void  when9And14Then50Percent() {
        Food product = new Food(
                "milk",
                LocalDate.of(2024, 12, 9),
                LocalDate.of(2024, 12, 14),
                100);
        Calculate calculate = new CalculatePercentExpiry();
        assertThat(calculate.percentFresh(product, now)).isCloseTo(50.0, within(0.1));
    }

    @Test
    public void  when10And11Then100Percent() {
        Food product = new Food(
                "milk",
                LocalDate.of(2024, 12, 10),
                LocalDate.of(2024, 12, 11),
                100);
        Calculate calculate = new CalculatePercentExpiry();
        assertThat(calculate.percentFresh(product, now)).isCloseTo(100.0, within(0.1));
    }

    @Test
    public void  when9And10Then150Percent() {
        Food product = new Food(
                "milk",
                LocalDate.of(2024, 12, 9),
                LocalDate.of(2024, 12, 10),
                100);
        Calculate calculate = new CalculatePercentExpiry();
        assertThat(calculate.percentFresh(product, now)).isCloseTo(150.0, within(0.1));
    }

    @Test
    public void  when11And14Then25Percent() {
        Food product = new Food(
                "milk",
                LocalDate.of(2024, 12, 11),
                LocalDate.of(2024, 12, 14),
                100);
        Calculate calculate = new CalculatePercentExpiry();
        assertThat(calculate.percentFresh(product, now)).isCloseTo(25.0, within(0.1));
    }

    @Test
    public void  when6And13Then75Percent() {
        Food product = new Food(
                "milk",
                LocalDate.of(2024, 12, 6),
                LocalDate.of(2024, 12, 13),
                100);
        Calculate calculate = new CalculatePercentExpiry();
        assertThat(calculate.percentFresh(product, now)).isCloseTo(75.0, within(0.1));
    }

    @Test
    public void  when12And13Then0Percent() {
        Food product = new Food(
                "milk",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 13),
                100);
        Calculate calculate = new CalculatePercentExpiry();
        assertThat(calculate.percentFresh(product, now)).isCloseTo(0.0, within(0.1));
    }
}
