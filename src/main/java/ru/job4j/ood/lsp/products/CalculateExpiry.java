package ru.job4j.ood.lsp.products;


import ru.job4j.ood.lsp.products.model.Food;

import java.time.LocalDate;

public interface CalculateExpiry {
    double percentFresh(Food product, LocalDate now);
    void setSale(Food product, double percentFresh);
}
