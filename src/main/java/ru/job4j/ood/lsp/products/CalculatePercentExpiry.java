package ru.job4j.ood.lsp.products;

import ru.job4j.ood.lsp.products.model.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculatePercentExpiry implements CalculateExpiry {

    private final static float DISCOUNT_MIN_PERCENT = 75f;
    private final static float DISCOUNT_PROPORTION = 0.2f;

    @Override
    public double percentFresh(Food product, LocalDate now) {
        LocalDate start = product.getCreateDate();
        LocalDate end = product.getExpiryDate();
        double totalDays = ChronoUnit.DAYS.between(start, end.plusDays(1));
        double passedDays = ChronoUnit.DAYS.between(start, now);
        return start.isEqual(end) ? 99 : (passedDays / totalDays) * 100;
    }

    @Override
    public void setSale(Food product, double percentFresh) {
        if (percentFresh > DISCOUNT_MIN_PERCENT) {
            product.setDiscount(product.getPrice() - product.getPrice() * DISCOUNT_PROPORTION);
        } else {
            product.setDiscount(product.getPrice());
        }
    }
}
