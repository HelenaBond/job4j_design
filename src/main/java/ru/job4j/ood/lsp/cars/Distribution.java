package ru.job4j.ood.lsp.cars;

import ru.job4j.ood.lsp.cars.model.park.Park;
import ru.job4j.ood.lsp.cars.model.park.Ticket;
import ru.job4j.ood.lsp.cars.parking.Parking;

public class Distribution {

    private final Park park;

    public Distribution(Park park) {
        this.park = park;
    }

    public Ticket parked(Parking parking) {
        return null;
    }

    public void vacated(Ticket ticket) {
    }
}
