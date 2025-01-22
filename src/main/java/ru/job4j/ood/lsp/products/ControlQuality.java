package ru.job4j.ood.lsp.products;

import ru.job4j.ood.lsp.products.model.Food;
import ru.job4j.ood.lsp.products.store.Store;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {

    private final List<Store> stores;

    private final CalculateExpiry calculateExpiry;

    public ControlQuality(List<Store> stores, CalculateExpiry calculateExpiry) {
        this.stores = stores;
        this.calculateExpiry = calculateExpiry;
    }

    public void move(Food product, LocalDate now) {
        double percentFresh = calculateExpiry.percentFresh(product, now);
        calculateExpiry.setSale(product, percentFresh);
        save(product, now, percentFresh);
    }

    public void resort(LocalDate now) {
        for (Store store : stores) {
            List<Food> currentStorage = store.getAll();
            for (int i = 0; i < currentStorage.size(); i++) {
                Food product = currentStorage.get(i);
                double percentFresh = calculateExpiry.percentFresh(product, now);
                if (now.isEqual(product.getMoveDate())) {
                    break;
                }
                if (store.delete(i, percentFresh).isPresent()) {
                    save(product, now, percentFresh);
                }
            }
        }
    }

    private void save(Food product, LocalDate now, double percentFresh) {
        product.setMoveDate(now);
        for (Store store : stores) {
            store.add(product, percentFresh);
        }
    }
}
