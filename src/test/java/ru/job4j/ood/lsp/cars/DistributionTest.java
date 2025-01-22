package ru.job4j.ood.lsp.cars;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.cars.model.Ticket;
import ru.job4j.ood.lsp.cars.parking.Parking;
import ru.job4j.ood.lsp.cars.parking.ParkingCar;
import ru.job4j.ood.lsp.cars.parking.ParkingTruck;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DistributionTest {

    private static int carPlace;
    private static int truckPlace;
    private Map<Integer, Boolean[]> park;

    @BeforeAll
    public static void init() {
        carPlace = 1;
        truckPlace = 2;
    }

    @BeforeEach
    public void initPark() {
        park = Map.of(
                carPlace, new Boolean[] {false, false, false, false},
                truckPlace, new Boolean[] {false, false, false, false}
        );
    }

    @Test
    public void whenCarIsParkedIntoRightPlace() {
        Parking currentCar = new ParkingCar();
        Distribution distribution = new Distribution(park);
        Ticket actualParking = distribution.parked(currentCar);
        assertThat(actualParking.position()).isEqualTo(1);
        assertThat(park.get(carPlace)[0]).isTrue();
        assertThat(park.get(carPlace)[1]).isFalse();
        assertThat(park.get(truckPlace)[0]).isFalse();
    }

    @Test
    public void whenTruckIsParkedIntoTruckPlace() {
        Parking currentTruck = new ParkingTruck(2);
        Distribution distribution = new Distribution(park);
        Ticket actualParking = distribution.parked(currentTruck);
        assertThat(actualParking.position()).isEqualTo(1);
        assertThat(park.get(carPlace)[0]).isFalse();
        assertThat(park.get(truckPlace)[0]).isTrue();
        assertThat(park.get(truckPlace)[1]).isTrue();
        assertThat(park.get(truckPlace)[2]).isFalse();
    }

    @Test
    public void whenVeryBigTruckIsParkedIntoCarPlace() {
        Parking currentTruck = new ParkingTruck(3);
        Distribution distribution = new Distribution(park);
        Ticket actualParking = distribution.parked(currentTruck);
        assertThat(actualParking.position()).isEqualTo(1);
        assertThat(park.get(truckPlace)[0]).isFalse();
        assertThat(park.get(carPlace)[0]).isTrue();
        assertThat(park.get(carPlace)[1]).isTrue();
        assertThat(park.get(carPlace)[2]).isTrue();
        assertThat(park.get(carPlace)[3]).isFalse();
    }

    @Test
    public void whenVacatedSuccessful() {
        Parking currentCar = new ParkingCar();
        Distribution distribution = new Distribution(park);
        Ticket actualParking = distribution.parked(currentCar);
        distribution.vacated(actualParking);
        assertThat(park.get(carPlace)[0]).isFalse();
        assertThat(park.get(truckPlace)[0]).isFalse();
    }

    @Test
    public void whenVeryBigTruckIsNotParkedIntoCarPlace() {
        Parking currentCar = new ParkingCar();
        Parking currentTruck = new ParkingTruck(3);
        Distribution distribution = new Distribution(park);
        Ticket parkingCar1 = distribution.parked(currentCar);
        Ticket parkingCar2 = distribution.parked(currentCar);
        distribution.vacated(parkingCar1);
        assertThatThrownBy(() -> distribution.parked(currentTruck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Под ваши габариты мест нет.");
        assertThat(parkingCar2.position()).isEqualTo(2);
        assertThat(park.get(truckPlace)[0]).isFalse();
        assertThat(park.get(carPlace)[0]).isFalse();
        assertThat(park.get(carPlace)[1]).isTrue();
        assertThat(park.get(carPlace)[2]).isFalse();
    }
}
