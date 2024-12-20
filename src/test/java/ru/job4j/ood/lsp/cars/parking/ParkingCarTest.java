package ru.job4j.ood.lsp.cars.parking;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.cars.model.park.Park;
import ru.job4j.ood.lsp.cars.model.park.Ticket;
import ru.job4j.ood.lsp.cars.model.transport.Car;
import ru.job4j.ood.lsp.cars.model.transport.Truck;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParkingCarTest {
    @Test
    public void whenCarIsParked() {
        Car car = new Car();
        Park park = new Park(Map.of(car, new Boolean[] {false, false}));
        Parking currentCar = new ParkingCar(new Car());
        Ticket actual = currentCar.parked(park);
        assertThat(actual).isNotNull();
        assertThat(actual.position()).isEqualTo(1);
        assertThat(actual.transport()).isEqualTo(new Car());
        assertThat(park.places().get(car)[0]).isTrue();
        assertThat(park.places().get(car)[1]).isFalse();
    }

    @Test
    public void whenCarIsNotParked() {
        Car truck = new Truck(2);
        Park park = new Park(Map.of(truck, new Boolean[] {false, false, false}));
        Parking car = new ParkingCar(new Car());
        Ticket actual = car.parked(park);
        assertThat(actual).isNull();
        assertThat(park.places().get(truck)[0]).isFalse();
        assertThat(park.places().get(truck)[1]).isFalse();
        assertThat(park.places().get(truck)[2]).isFalse();
    }
}
