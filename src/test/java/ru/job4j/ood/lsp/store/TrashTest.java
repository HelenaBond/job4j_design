package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Food;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TrashTest {

    public static Food expected;

    @BeforeAll
    public static void init() {
        expected = new Food(
                "milk",
                LocalDate.now(),
                LocalDate.now(),
                100);
    }

    @Test
    public void whenMove100PercentThenSuccessfully() {
        Store trash = new Trash();
        trash.add(expected, 100);
        assertThat(trash.getAll().get(0)).isEqualTo(expected);
    }

    @Test
    public void whenMove110PercentThenSuccessfully() {
        Store trash = new Trash();
        trash.add(expected, 110);
        assertThat(trash.getAll().get(0)).isEqualTo(expected);
    }
}
