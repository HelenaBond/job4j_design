package ru.job4j.ood.lsp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.products.model.Food;
import ru.job4j.ood.lsp.products.CalculatePercentExpiry;
import ru.job4j.ood.lsp.products.ControlQuality;
import ru.job4j.ood.lsp.products.store.Shop;
import ru.job4j.ood.lsp.products.store.Store;
import ru.job4j.ood.lsp.products.store.Trash;
import ru.job4j.ood.lsp.products.store.Warehouse;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ControlQualityTest {

    public static LocalDate now;
    public List<Store> stores;

    @BeforeAll
    public static void initDate() {
        now = LocalDate.of(2024, 12, 12);
    }

    @BeforeEach
    public void init() {
        stores = List.of(
                new Trash(),
                new Warehouse(),
                new Shop()
        );
    }

    @Test
    public void whenOneDayFreshThenDiscountInShop() {
        CalculateExpiry calculateExpiry = new CalculatePercentExpiry();
        ControlQuality controlQuality = new ControlQuality(stores, calculateExpiry);
        Food expected = new Food(
                "milk",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 12),
                100);
        controlQuality.move(expected, now);
        Food actual = stores.get(2).getAll().get(0);
        double expectedDiscount = actual.getPrice() - actual.getPrice() * 0.2;
        assertThat(actual).isEqualTo(expected);
        assertThat(actual.getDiscount()).isCloseTo(expectedDiscount, within(0.1));
    }

    @Test
    public void whenFirstDayFreshThenInWarehouse() {
        CalculateExpiry calculateExpiry = new CalculatePercentExpiry();
        ControlQuality controlQuality = new ControlQuality(stores, calculateExpiry);
        Food expected = new Food(
                "milk",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 13),
                100);
        controlQuality.move(expected, now);
        Food actual = stores.get(1).getAll().get(0);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenAllResort() {
        CalculateExpiry calculateExpiry = new CalculatePercentExpiry();
        ControlQuality controlQuality = new ControlQuality(stores, calculateExpiry);
        Food product1 = new Food(
                "milk",
                LocalDate.of(2024, 12, 11),
                LocalDate.of(2024, 12, 12),
                100);
        Food product2 = new Food(
                "milk",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 13),
                100);
        controlQuality.move(product1, now);
        controlQuality.move(product2, now);
        LocalDate tomorrow = now.plusDays(1);
        controlQuality.resort(tomorrow);
    }
}
