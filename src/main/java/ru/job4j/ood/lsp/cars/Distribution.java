package ru.job4j.ood.lsp.cars;

import ru.job4j.ood.lsp.cars.model.Park;
import ru.job4j.ood.lsp.cars.service.Parking;

public class Distribution {

    private final Park park;

    public Distribution(Park park) {
        this.park = park;
    }

    public boolean isParking(Parking parking) {
        return false;
    }
}
