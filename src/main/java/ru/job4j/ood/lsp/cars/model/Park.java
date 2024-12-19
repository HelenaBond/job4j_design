package ru.job4j.ood.lsp.cars.model;

import ru.job4j.ood.lsp.cars.service.Parking;

import java.util.Map;

public class Park {
    private final Map<Parking, Integer> places;

    public Park(Map<Parking, Integer> places) {
        this.places = places;
    }

    public Map<Parking, Integer> getPlaces() {
        return places;
    }
}
