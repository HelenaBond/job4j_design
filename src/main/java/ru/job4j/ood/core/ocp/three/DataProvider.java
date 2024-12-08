package ru.job4j.ood.core.ocp.three;

import java.util.ArrayList;

public class DataProvider {
    /**
     * Заставляет клиента этого кода зависеть от конкретной реализации.
     */
    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("Item 1");
        data.add("Item 2");
        data.add("Item 3");
        return data;
    }
}
