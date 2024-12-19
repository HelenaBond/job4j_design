package ru.job4j.ood.lsp.cars.parking;

import ru.job4j.ood.lsp.cars.model.park.Park;
import ru.job4j.ood.lsp.cars.model.transport.Truck;

public class ParkingTruck implements Parking {

    private Truck truck;

    public ParkingTruck(Truck truck) {
        this.truck = truck;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    @Override
    public boolean parking(Park park) {
        return false;
    }
}
