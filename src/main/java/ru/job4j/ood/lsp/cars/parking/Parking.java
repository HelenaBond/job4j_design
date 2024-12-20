package ru.job4j.ood.lsp.cars.parking;

import ru.job4j.ood.lsp.cars.model.park.Park;
import ru.job4j.ood.lsp.cars.model.park.Ticket;
import ru.job4j.ood.lsp.cars.model.transport.Car;

public interface Parking {
    Ticket parked(Park park);

    default int getIndex(Boolean[] places, Car transport) {
        if (places != null && places.length > 0) {
            int left = 0;
            int right = 0;
            int size = transport.getSize();
            while (size <= places.length - left) {
                if (!places[right]) {
                    if (right - left == size - 1) {
                        return left;
                    }
                    right++;
                } else {
                    left = right;
                    right++;
                    left++;
                }
            }
        }
        return -1;
    }
}
