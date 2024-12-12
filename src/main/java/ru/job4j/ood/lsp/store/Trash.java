package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

public class Trash extends AbstractStore {
    @Override
    public void move(Food product, double percentFresh) {
        if (percentFresh >= 100) {
            add(product);
        }
    }
}
