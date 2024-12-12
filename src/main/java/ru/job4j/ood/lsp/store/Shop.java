package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

public class Shop extends AbstractStore {

    @Override
    public void move(Food product, double percentFresh) {
        if (percentFresh == ONE_DAY_FRESH
                || percentFresh > WAREHOUSE_MAX_PERCENT && percentFresh < TRASH_MIN_PERCENT) {
            add(product);
            if (percentFresh == ONE_DAY_FRESH || percentFresh > DISCOUNT_MIN_PERCENT) {
                product.setDiscount(product.getPrice() - product.getPrice() * DISCOUNT_PROPORTION);
            }
        }
    }
}
