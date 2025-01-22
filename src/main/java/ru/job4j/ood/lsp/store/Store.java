package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

import java.util.List;
import java.util.Optional;

public interface Store {
    void add(Food product, double percentFresh);
    Optional<Food> delete(int index, double percentFresh);
    List<Food> getAll();
}
