package ru.job4j.ood.lsp.cars.model.park;

import ru.job4j.ood.lsp.cars.model.transport.Transport;

import java.util.Map;

public record Park(Map<Transport, Boolean[]> places) {
}
