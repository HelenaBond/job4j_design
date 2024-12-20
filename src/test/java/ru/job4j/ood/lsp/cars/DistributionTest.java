package ru.job4j.ood.lsp.cars;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.cars.model.park.Park;
import ru.job4j.ood.lsp.cars.model.park.Ticket;
import ru.job4j.ood.lsp.cars.model.transport.Car;
import ru.job4j.ood.lsp.cars.model.transport.Truck;
import ru.job4j.ood.lsp.cars.parking.Parking;
import ru.job4j.ood.lsp.cars.parking.ParkingCar;
import ru.job4j.ood.lsp.cars.parking.ParkingTruck;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DistributionTest {

    private static Car car;
    private static Truck truck;
    private Park park;

    @BeforeAll
    public static void init() {
        car = new Car();
        truck = new Truck(2);
    }

    @BeforeEach
    public void initPark() {
        Map<Car, Boolean[]> places = Map.of(
                car, new Boolean[] {false, false, false, false},
                truck, new Boolean[] {false, false, false, false}
        );
        park = new Park(places);
    }

    @Test
    public void whenCarIsParkedIntoRightPlace() {
        Parking currentCar = new ParkingCar(car);
        Distribution distribution = new Distribution(park);
        Ticket actualParking = distribution.parked(currentCar);
        assertThat(actualParking.position()).isEqualTo(1);
        assertThat(park.places().get(car)[0]).isTrue();
        assertThat(park.places().get(car)[1]).isFalse();
        assertThat(park.places().get(truck)[0]).isFalse();
    }

    @Test
    public void whenTruckIsParkedIntoTruckPlace() {
        Parking currentTruck = new ParkingTruck(new Truck(2), car);
        Distribution distribution = new Distribution(park);
        Ticket actualParking = distribution.parked(currentTruck);
        assertThat(actualParking.position()).isEqualTo(1);
        assertThat(park.places().get(car)[0]).isFalse();
        assertThat(park.places().get(truck)[0]).isTrue();
        assertThat(park.places().get(truck)[1]).isTrue();
        assertThat(park.places().get(truck)[2]).isFalse();
    }

    @Test
    public void whenVeryBigTruckIsParkedIntoCarPlace() {
        Parking currentTruck = new ParkingTruck(new Truck(3), car);
        Distribution distribution = new Distribution(park);
        Ticket actualParking = distribution.parked(currentTruck);
        assertThat(actualParking.position()).isEqualTo(1);
        assertThat(park.places().get(truck)[0]).isFalse();
        assertThat(park.places().get(car)[0]).isTrue();
        assertThat(park.places().get(car)[1]).isTrue();
        assertThat(park.places().get(car)[2]).isTrue();
        assertThat(park.places().get(car)[3]).isFalse();
    }

    @Test
    public void whenVacatedSuccessful() {
        Parking currentCar = new ParkingCar(car);
        Distribution distribution = new Distribution(park);
        Ticket actualParking = distribution.parked(currentCar);
        distribution.vacated(actualParking);
        assertThat(park.places().get(car)[0]).isFalse();
        assertThat(park.places().get(truck)[0]).isFalse();
    }

    @Test
    public void whenVeryBigTruckIsNotParkedIntoCarPlace() {
        Parking currentCar = new ParkingCar(car);
        Parking currentTruck = new ParkingTruck(new Truck(3), car);
        Distribution distribution = new Distribution(park);
        Ticket parkingCar1 = distribution.parked(currentCar);
        Ticket parkingCar2 = distribution.parked(currentCar);
        distribution.vacated(parkingCar1);
        Ticket actualParkingTruck = distribution.parked(currentTruck);
        assertThat(actualParkingTruck).isNull();
        assertThat(parkingCar2.position()).isEqualTo(2);
        assertThat(park.places().get(truck)[0]).isFalse();
        assertThat(park.places().get(car)[0]).isFalse();
        assertThat(park.places().get(car)[1]).isTrue();
        assertThat(park.places().get(car)[2]).isFalse();
    }
}
