package ru.job4j.ood.lsp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Food;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ControlQualityTest {

    public static LocalDate now;

    @BeforeAll
    public static void init() {
        now = LocalDate.of(2024, 12, 12);
    }

    @Test
    public void  when12And12Then0Percent() {
        Food product = new Food("milk",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 12), 100);
        ControlQuality control = new ControlQuality();
        assertThat(control.percentFresh(product, now)).isEqualTo(0.0);
    }

    @Test
    public void  when11And12Then50Percent() {
        Food product = new Food("milk",
                LocalDate.of(2024, 12, 11),
                LocalDate.of(2024, 12, 12), 100);
        ControlQuality control = new ControlQuality();
        assertThat(control.percentFresh(product, now)).isEqualTo(50.0);
    }

    @Test
    public void  when9And14Then50Percent() {
        Food product = new Food("milk",
                LocalDate.of(2024, 12, 9),
                LocalDate.of(2024, 12, 14), 100);
        ControlQuality control = new ControlQuality();
        assertThat(control.percentFresh(product, now)).isEqualTo(50.0);
    }

    @Test
    public void  when10And11Then100Percent() {
        Food product = new Food("milk",
                LocalDate.of(2024, 12, 10),
                LocalDate.of(2024, 12, 11), 100);
        ControlQuality control = new ControlQuality();
        assertThat(control.percentFresh(product, now)).isEqualTo(100.0);
    }

    @Test
    public void  when9And10Then100Percent() {
        Food product = new Food("milk",
                LocalDate.of(2024, 12, 9),
                LocalDate.of(2024, 12, 10), 100);
        ControlQuality control = new ControlQuality();
        assertThat(control.percentFresh(product, now)).isEqualTo(150.0);
    }

    @Test
    public void  when11And14Then25Percent() {
        Food product = new Food("milk",
                LocalDate.of(2024, 12, 11),
                LocalDate.of(2024, 12, 14), 100);
        ControlQuality control = new ControlQuality();
        assertThat(control.percentFresh(product, now)).isEqualTo(25.0);
    }

    @Test
    public void  when9And13Then75Percent() {
        Food product = new Food("milk",
                LocalDate.of(2024, 12, 6),
                LocalDate.of(2024, 12, 13), 100);
        ControlQuality control = new ControlQuality();
        assertThat(control.percentFresh(product, now)).isEqualTo(75.0);
    }
}
