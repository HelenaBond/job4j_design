package ru.job4j.ood.lsp.cars.model.park;

import ru.job4j.ood.lsp.cars.model.transport.Car;

public record Ticket(Car transport, int position) {
}
