package ru.job4j.ood.lsp.cars;

import ru.job4j.ood.lsp.cars.model.Ticket;
import ru.job4j.ood.lsp.cars.parking.Parking;

import java.util.Map;
import java.util.Optional;

public class Distribution {

    private final Map<Integer, Boolean[]> park;

    public Distribution(Map<Integer, Boolean[]> park) {
        this.park = park;
    }

    public Ticket parked(Parking parking) {
        Optional<Ticket> ticket = parking.parked(park);
        return ticket.orElseThrow(() -> new IllegalArgumentException(
                "Под ваши габариты мест нет.")
        );
    }

    public void vacated(Ticket ticket) {
        Boolean[] places = park.get(ticket.parkingSpaceLength());
        for (int i = ticket.position() - 1; i < ticket.parkingSpaceLength(); i++) {
            places[i] = false;
        }
    }
}
