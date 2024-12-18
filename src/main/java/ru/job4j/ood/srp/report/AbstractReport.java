package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.store.Store;

public abstract class AbstractReport implements Report {
    private final Store store;

    public AbstractReport(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return store;
    }
}
