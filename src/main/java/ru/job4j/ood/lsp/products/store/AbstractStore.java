package ru.job4j.ood.lsp.products.store;


import ru.job4j.ood.lsp.products.model.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    protected final static float TRASH_MIN_PERCENT = 100f;
    protected final static float WAREHOUSE_MAX_PERCENT = 25f;
    protected final static float FIRST_DAY_FRESH = 0f;

    private final List<Food> products = new ArrayList<>();

    protected void addIn(Food product) {
        products.add(product);
    }

    protected Food deleteFrom(int index) {
        return products.remove(index);
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(products);
    }
}
