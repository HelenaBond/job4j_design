package ru.job4j.ood.lsp.cars.parking;
import ru.job4j.ood.lsp.cars.model.park.Park;
import ru.job4j.ood.lsp.cars.model.park.Ticket;
import ru.job4j.ood.lsp.cars.model.transport.Car;

public record ParkingCar(Car car) implements Parking {

    @Override
    public Ticket parked(Park park) {
        Boolean[] places = park.places().get(car);
        int index = getIndex(places, car);
        if (index > -1) {
            places[index] = true;
            return new Ticket(car, index + 1);
        }
        return null;
    }
}
