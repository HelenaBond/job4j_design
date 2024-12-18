package ru.job4j.ood.lsp.products;

import ru.job4j.ood.lsp.products.model.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculatePercentExpiry {

    public double percentFresh(Food product, LocalDate now) {
        LocalDate start = product.getCreateDate();
        LocalDate end = product.getExpiryDate();
        double totalDays = ChronoUnit.DAYS.between(start, end.plusDays(1));
        double passedDays = ChronoUnit.DAYS.between(start, now);
        return start.isEqual(end) ? 99 : (passedDays / totalDays) * 100;
    }
}
