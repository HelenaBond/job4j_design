package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Food;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WarehouseTest {

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
    public void whenMove0Dot1PercentThenSuccessfully() {
        Store warehouse = new Warehouse();
        warehouse.add(expected, 0.1);
        assertThat(warehouse.getAll().get(0)).isEqualTo(expected);
    }

    @Test
    public void whenMove25PercentThenSuccessfully() {
        Store warehouse = new Warehouse();
        warehouse.add(expected, 25);
        assertThat(warehouse.getAll().get(0)).isEqualTo(expected);
    }
}
