package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private final List<Food> products = new ArrayList<>();

    @Override
    public void add(Food product) {
        products.add(product);
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(products);
    }
}
