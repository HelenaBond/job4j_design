package ru.job4j.ood.lsp.cars.parking;

import ru.job4j.ood.lsp.cars.model.Ticket;

import java.util.Map;
import java.util.Optional;

public class ParkingTruck implements Parking {

    private final int parkingSpaceLength;

    public ParkingTruck(int parkingSpaceLength) {
        this.parkingSpaceLength = parkingSpaceLength;
    }

    @Override
    public Optional<Ticket> parked(Map<Integer, Boolean[]> park) {
        Boolean[] places = park.get(parkingSpaceLength);
        int transportType = parkingSpaceLength;
        int index = getIndex(places, parkingSpaceLength);
        if (index == -1) {
            transportType = MIN_LENGTH;
            places = park.get(transportType);
            index = getIndex(places, parkingSpaceLength);

        }
        if (index != -1) {
            for (int i = index; i < parkingSpaceLength; i++) {
                places[i] = true;
            }
            return Optional.of(new Ticket(transportType, index + 1));
        }
        return Optional.empty();
    }
}
