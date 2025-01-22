package ru.job4j.ood.lsp.cars.parking;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.cars.model.Ticket;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingCarTest {
    @Test
    public void whenCarIsParked() {
        int carPlace = 1;
        Map<Integer, Boolean[]> park = Map.of(carPlace, new Boolean[] {false, false});
        Parking currentCar = new ParkingCar();
        Optional<Ticket> actual = currentCar.parked(park);
        assertTrue(actual.isPresent());
        assertThat(actual.get().position()).isEqualTo(1);
        assertThat(actual.get().parkingSpaceLength()).isEqualTo(carPlace);
        assertThat(park.get(carPlace)[0]).isTrue();
        assertThat(park.get(carPlace)[1]).isFalse();
    }

    @Test
    public void whenCarIsNotParked() {
        int truckPlace = 2;
        Map<Integer, Boolean[]> park = Map.of(truckPlace, new Boolean[] {false, false, false});
        Parking car = new ParkingCar();
        Optional<Ticket> actual = car.parked(park);
        assertTrue(actual.isEmpty());
        assertThat(park.get(truckPlace)[0]).isFalse();
        assertThat(park.get(truckPlace)[1]).isFalse();
        assertThat(park.get(truckPlace)[2]).isFalse();
    }
}
