package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

public class Shop extends AbstractStore {

    @Override
    public void move(Food product, double percentFresh) {
        if (percentFresh == 0 || percentFresh > 25 && percentFresh < 100) {
            add(product);
            if (percentFresh == 0 || percentFresh > 75) {
                product.setDiscount(product.getPrice() - product.getPrice() * 0.2);
            }
        }
    }
}
