package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.store.Store;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {

    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void moveTo(Food product, LocalDate now, CalculatePercentExpiry calculate) {
        double percentFresh = calculate.percentFresh(product, now);
        for (Store store : stores) {
            store.move(product, percentFresh);
        }
    }
}
