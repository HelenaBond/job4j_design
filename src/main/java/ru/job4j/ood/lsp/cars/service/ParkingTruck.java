package ru.job4j.ood.lsp.cars.service;

import ru.job4j.ood.lsp.cars.model.Park;

public class ParkingTruck extends ParkingCar {

    public ParkingTruck(int size) {
        super(size);
    }

    @Override
    public boolean parking(Park park) {
        return false;
    }
}
