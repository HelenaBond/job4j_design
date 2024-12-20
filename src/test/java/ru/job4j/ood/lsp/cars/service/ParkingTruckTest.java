package ru.job4j.ood.lsp.cars.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.cars.model.park.Park;
import ru.job4j.ood.lsp.cars.model.transport.Car;
import ru.job4j.ood.lsp.cars.model.transport.Transport;
import ru.job4j.ood.lsp.cars.model.transport.Truck;
import ru.job4j.ood.lsp.cars.parking.Parking;
import ru.job4j.ood.lsp.cars.parking.ParkingTruck;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Disabled("Тесты не активны пока не будет создана реализация.")
class ParkingTruckTest {

    @Test
    public void whenTrackInTruckPlace() {
        Transport truck = new Truck(2);
        Park park = new Park(Map.of(truck, new Boolean[2]));
        Parking currentTruck = new ParkingTruck(new Truck(2));
        boolean actual = currentTruck.parking(park);
        assertThat(actual).isTrue();
        assertThat(park.places().get(truck)[0]).isTrue();
        assertThat(park.places().get(truck)[1]).isTrue();
    }

    @Test
    public void whenTruckInCarPlace() {
        Transport car = new Car(1);
        Park park = new Park(Map.of(car, new Boolean[2]));
        Parking truck = new ParkingTruck(new Truck(2));
        boolean actual = truck.parking(park);
        assertThat(actual).isTrue();
        assertThat(park.places().get(car)[0]).isTrue();
        assertThat(park.places().get(car)[1]).isTrue();
    }

    @Test
    public void whenNoPlaceToTruck() {
        Transport car = new Car(1);
        Park park = new Park(Map.of(car, new Boolean[1]));
        Parking truck = new ParkingTruck(new Truck(2));
        boolean actual = truck.parking(park);
        assertThat(actual).isFalse();
        assertThat(park.places().get(car)[0]).isFalse();
    }
}
