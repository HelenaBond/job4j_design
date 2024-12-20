package ru.job4j.ood.lsp.cars.parking;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.cars.model.park.Park;
import ru.job4j.ood.lsp.cars.model.park.Ticket;
import ru.job4j.ood.lsp.cars.model.transport.Car;
import ru.job4j.ood.lsp.cars.model.transport.Truck;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParkingTruckTest {

    @Test
    public void whenTrackInTruckPlace() {
        Car car = new Car();
        Car truck = new Truck(2);
        Park park = new Park(Map.of(truck, new Boolean[] {false, false}));
        Parking currentTruck = new ParkingTruck(new Truck(2), car);
        Ticket actual = currentTruck.parked(park);
        assertThat(actual).isNotNull();
        assertThat(actual.position()).isEqualTo(1);
        assertThat(actual.transport()).isEqualTo(car);
        assertThat(park.places().get(truck)[0]).isTrue();
        assertThat(park.places().get(truck)[1]).isTrue();
    }

    @Test
    public void whenTruckInCarPlace() {
        Car car = new Car();
        Park park = new Park(Map.of(car, new Boolean[] {false, false}));
        Parking truck = new ParkingTruck(new Truck(2), car);
        Ticket actual = truck.parked(park);
        assertThat(actual).isNotNull();
        assertThat(actual.position()).isEqualTo(1);
        assertThat(actual.transport()).isEqualTo(car);
        assertThat(park.places().get(car)[0]).isTrue();
        assertThat(park.places().get(car)[1]).isTrue();
    }

    @Test
    public void whenNoPlaceToTruck() {
        Car car = new Car();
        Park park = new Park(Map.of(car, new Boolean[] {false}));
        Parking truck = new ParkingTruck(new Truck(2), car);
        Ticket actual = truck.parked(park);
        assertThat(actual).isNull();
        assertThat(park.places().get(car)[0]).isFalse();
    }
}
