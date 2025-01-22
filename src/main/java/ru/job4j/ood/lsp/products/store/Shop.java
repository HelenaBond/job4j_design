package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.model.Food;

import java.util.Optional;

public class Shop extends AbstractStore {

    @Override
    public void add(Food product, double percentFresh) {
        if (check(percentFresh)) {
            addIn(product);
        }
    }

    @Override
    public Optional<Food> delete(int index, double percentFresh) {
        if (!check(percentFresh)) {
            return Optional.of(deleteFrom(index));
        }
        return Optional.empty();
    }

    private boolean check(double percentFresh) {
        return percentFresh > WAREHOUSE_MAX_PERCENT && percentFresh < TRASH_MIN_PERCENT;
    }
}
