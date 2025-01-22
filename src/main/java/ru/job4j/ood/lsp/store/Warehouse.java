package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

import java.util.Optional;

public class Warehouse extends AbstractStore {

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
        return percentFresh >= FIRST_DAY_FRESH && percentFresh <= WAREHOUSE_MAX_PERCENT;
    }
}
