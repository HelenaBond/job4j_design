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
        return parking.parked(park);
    }

    public void vacated(Ticket ticket) {
        Boolean[] places = park.places().get(ticket.transport());
        for (int i = ticket.position() - 1; i < ticket.transport().getSize(); i++) {
            places[i] = false;
        }
    }
}
