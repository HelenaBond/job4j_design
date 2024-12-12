package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

public class Warehouse extends AbstractStore {

    @Override
    public void move(Food product, double percentFresh) {
        if (percentFresh > 0 && percentFresh <= 25) {
            add(product);
        }
    }
}
