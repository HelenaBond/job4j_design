package ru.job4j.ood.lsp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.store.*;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ControlQualityTest {

    public static LocalDate now;
    public static List<Store> stores;

    @BeforeAll
    public static void init() {
        now = LocalDate.of(2024, 12, 12);
        stores = List.of(
                new Trash(),
                new Warehouse(),
                new Shop()
        );
    }

    @Test
    public void whenOneDayFreshThenDiscountInShop() {
        ControlQuality controlQuality = new ControlQuality(stores, new CalculatePercentExpiry());
        Food expected = new Food(
                "milk",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 12),
                100);
        controlQuality.moveTo(expected, now);
        Food actual = stores.get(2).getAll().get(0);
        double expectedDiscount = actual.getPrice() - actual.getPrice() * AbstractStore.DISCOUNT_PROPORTION;
        assertThat(actual).isEqualTo(expected);
        assertThat(actual.getDiscount()).isCloseTo(expectedDiscount, within(0.1));
    }
}
