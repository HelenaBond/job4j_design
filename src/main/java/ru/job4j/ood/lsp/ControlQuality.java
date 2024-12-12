package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.store.Shop;
import ru.job4j.ood.lsp.store.Store;
import ru.job4j.ood.lsp.store.Trash;
import ru.job4j.ood.lsp.store.Warehouse;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private final List<Store> stores = new ArrayList<>();

    public ControlQuality() {
        stores.add(new Warehouse());
        stores.add(new Shop());
        stores.add(new Trash());
    }

    public void moveTo(Food product, LocalDate now) {
        double percentFresh = percentFresh(product, now);
        for (Store store : stores) {
            store.move(product, percentFresh);
        }
    }

    public double percentFresh(Food product, LocalDate now) {
        LocalDate start = product.getCreateDate();
        LocalDate end = product.getExpiryDate();
        double totalDays = ChronoUnit.DAYS.between(start, end.plusDays(1));
        double passedDays = ChronoUnit.DAYS.between(start, now);
        return (passedDays / totalDays) * 100;
    }
}
