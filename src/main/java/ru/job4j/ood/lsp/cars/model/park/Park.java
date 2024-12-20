package ru.job4j.ood.lsp.cars.model.park;

import ru.job4j.ood.lsp.cars.model.transport.Car;

import java.util.Map;

public record Park(Map<Car, Boolean[]> places) {
}
