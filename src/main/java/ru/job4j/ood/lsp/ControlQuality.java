package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.store.Store;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {

    private final List<Store> stores;
    private final Calculate calculate;

    public ControlQuality(List<Store> stores, Calculate calculate) {
        this.stores = stores;
        this.calculate = calculate;
    }

    public void moveTo(Food product, LocalDate now) {
        double percentFresh = calculate.percentFresh(product, now);
        for (Store store : stores) {
            store.move(product, percentFresh);
        }
    }
}
