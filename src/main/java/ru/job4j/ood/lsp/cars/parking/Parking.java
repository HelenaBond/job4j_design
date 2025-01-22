package ru.job4j.ood.lsp.cars.parking;

import ru.job4j.ood.lsp.cars.model.Ticket;

import java.util.Map;
import java.util.Optional;

public interface Parking {

     int MIN_LENGTH = 1;

    Optional<Ticket> parked(Map<Integer, Boolean[]> park);

    default int getIndex(Boolean[] places, int length) {
        if (places != null && places.length > 0) {
            int left = 0;
            int right = 0;
            while (length <= places.length - left) {
                if (!places[right]) {
                    if (right - left == length - 1) {
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
