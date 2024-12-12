package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.model.Food;

import java.time.LocalDate;

public interface Calculate {

    double percentFresh(Food product, LocalDate now);
}
