package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.model.Food;

public class Trash extends AbstractStore {

    @Override
    public void move(Food product, double percentFresh) {
        if (percentFresh >= TRASH_MIN_PERCENT) {
            add(product);
        }
    }
}
