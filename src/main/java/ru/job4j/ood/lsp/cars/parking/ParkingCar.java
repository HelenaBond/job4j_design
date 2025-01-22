package ru.job4j.ood.lsp.cars.parking;
import ru.job4j.ood.lsp.cars.model.Ticket;

import java.util.Map;
import java.util.Optional;

public class ParkingCar implements Parking {

    @Override
    public Optional<Ticket> parked(Map<Integer, Boolean[]> park) {
        Boolean[] places = park.get(MIN_LENGTH);
        int index = getIndex(places, MIN_LENGTH);
        if (index > -1) {
            places[index] = true;
            return Optional.of(new Ticket(MIN_LENGTH, index + 1));
        }
        return Optional.empty();
    }
}
