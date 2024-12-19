package ru.job4j.ood.lsp.cars.service;

import ru.job4j.ood.lsp.cars.model.Park;

public class ParkingCar implements Parking {
    @Override
    public boolean parking(int size, Park park) {
        return false;
    }
}
