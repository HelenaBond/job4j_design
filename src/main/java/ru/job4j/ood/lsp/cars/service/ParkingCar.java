package ru.job4j.ood.lsp.cars.service;

import ru.job4j.ood.lsp.cars.model.Park;

public class ParkingCar implements Parking {

    private final int size;

    public ParkingCar(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean parking(Park park) {
        return false;
    }
}
