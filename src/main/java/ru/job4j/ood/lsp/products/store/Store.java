package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.model.Food;

import java.util.List;

public interface Store {

    void add(Food product);
    void move(Food product, double percentFresh);
    List<Food> getAll();
}
