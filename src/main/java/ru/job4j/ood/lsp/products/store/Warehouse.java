package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.model.Food;

public class Warehouse extends AbstractStore {

    @Override
    public void move(Food product, double percentFresh) {
        if (percentFresh >= FIRST_DAY_FRESH && percentFresh <= WAREHOUSE_MAX_PERCENT) {
            add(product);
        }
    }
}
