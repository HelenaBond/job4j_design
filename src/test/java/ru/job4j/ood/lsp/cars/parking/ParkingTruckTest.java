package ru.job4j.ood.lsp.cars.parking;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.cars.model.Ticket;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingTruckTest {

    @Test
    public void whenTrackInTruckPlace() {
        int truckPlace = 2;
        Map<Integer, Boolean[]> park = Map.of(truckPlace, new Boolean[] {false, false});
        Parking currentTruck = new ParkingTruck(2);
        Optional<Ticket> actual = currentTruck.parked(park);
        assertTrue(actual.isPresent());
        assertThat(actual.get().position()).isEqualTo(1);
        assertThat(actual.get().parkingSpaceLength()).isEqualTo(truckPlace);
        assertThat(park.get(truckPlace)[0]).isTrue();
        assertThat(park.get(truckPlace)[1]).isTrue();
    }

    @Test
    public void whenTruckInCarPlace() {
        int carPlace = 1;
        Map<Integer, Boolean[]> park = Map.of(carPlace, new Boolean[] {false, false});
        Parking truck = new ParkingTruck(2);
        Optional<Ticket> actual = truck.parked(park);
        assertTrue(actual.isPresent());
        assertThat(actual.get().position()).isEqualTo(1);
        assertThat(actual.get().parkingSpaceLength()).isEqualTo(carPlace);
        assertThat(park.get(carPlace)[0]).isTrue();
        assertThat(park.get(carPlace)[1]).isTrue();
    }

    @Test
    public void whenNoPlaceToTruck() {
        int carPlace = 1;
        Map<Integer, Boolean[]> park = Map.of(carPlace, new Boolean[] {false});
        Parking truck = new ParkingTruck(2);
        Optional<Ticket> actual = truck.parked(park);
        assertTrue(actual.isEmpty());
        assertThat(park.get(carPlace)[0]).isFalse();
    }
}
