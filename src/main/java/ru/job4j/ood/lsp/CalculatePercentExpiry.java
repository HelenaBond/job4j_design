package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.model.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculatePercentExpiry implements Calculate {

    @Override
    public double percentFresh(Food product, LocalDate now) {
        LocalDate start = product.getCreateDate();
        LocalDate end = product.getExpiryDate();
        double totalDays = ChronoUnit.DAYS.between(start, end.plusDays(1));
        double passedDays = ChronoUnit.DAYS.between(start, now);
        return start.isEqual(end) ? 99 : (passedDays / totalDays) * 100;
    }
}
