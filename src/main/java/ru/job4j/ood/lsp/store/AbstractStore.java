package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    public final static float TRASH_MIN_PERCENT = 100f;
    public final static float WAREHOUSE_MAX_PERCENT = 25f;
    public final static float ONE_DAY_FRESH = 0f;
    public final static float DISCOUNT_MIN_PERCENT = 75f;
    public final static float DISCOUNT_PROPORTION = 0.2f;

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
