package ru.job4j.ood.lsp.cars.parking;

import ru.job4j.ood.lsp.cars.model.park.Park;
import ru.job4j.ood.lsp.cars.model.park.Ticket;
import ru.job4j.ood.lsp.cars.model.transport.Car;
import ru.job4j.ood.lsp.cars.model.transport.Truck;

public record ParkingTruck(Truck truck, Car car) implements Parking {

    @Override
    public Ticket parked(Park park) {
        Boolean[] places = park.places().get(truck);
        int index = getIndex(places, truck);
        if (index == -1) {
            places = park.places().get(car);
            index = getIndex(places, truck);
        }
        if (index != -1) {
            for (int i = index; i < truck.getSize(); i++) {
                places[i] = true;
            }
            return new Ticket(car, index + 1);
        }
        return null;
    }
}
