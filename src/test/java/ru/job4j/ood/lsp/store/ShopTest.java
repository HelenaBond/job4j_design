package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Food;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ShopTest {

    public static int price;
    public static Food expected;

    @BeforeAll
    public static void init() {
        price = 100;
        expected = new Food(
                "milk",
                LocalDate.now(),
                LocalDate.now(),
                price);
    }

    @Test
    public void whenMove25Dot1PercentThenSuccessfully() {
        Store shop = new Shop();
        shop.move(expected, 25.1);
        assertThat(shop.getAll().get(0)).isEqualTo(expected);
    }

    @Test
    public void whenMove99Dot9PercentThenSuccessfullyWithDiscount() {
        Store shop = new Shop();
        shop.move(expected, 99.9);
        Food actual = shop.getAll().get(0);
        assertThat(actual).isEqualTo(expected);
        double discount = price - (price * AbstractStore.DISCOUNT_PROPORTION);
        assertThat(actual.getDiscount()).isCloseTo(discount, within(0.1));
    }
}
