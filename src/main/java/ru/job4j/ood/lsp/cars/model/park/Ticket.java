package ru.job4j.ood.lsp.cars.model.park;

import ru.job4j.ood.lsp.cars.model.transport.Transport;

public record Ticket(Transport transport, int position) {
}
