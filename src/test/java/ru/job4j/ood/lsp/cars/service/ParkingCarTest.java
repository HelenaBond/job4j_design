package ru.job4j.ood.lsp.cars.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.cars.model.park.Park;
import ru.job4j.ood.lsp.cars.model.transport.Car;
import ru.job4j.ood.lsp.cars.model.transport.Transport;
import ru.job4j.ood.lsp.cars.model.transport.Truck;
import ru.job4j.ood.lsp.cars.parking.Parking;
import ru.job4j.ood.lsp.cars.parking.ParkingCar;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Disabled("Тесты не активны пока не будет создана реализация.")
class ParkingCarTest {
    @Test
    public void whenCarIsParked() {
        Transport car = new Car(1);
        Park park = new Park(Map.of(car, new Boolean[2]));
        Parking currentCar = new ParkingCar(new Car(1));
        boolean actual = currentCar.parking(park);
        assertThat(actual).isTrue();
        assertThat(park.places().get(car)[0]).isTrue();
        assertThat(park.places().get(car)[1]).isFalse();
    }

    @Test
    public void whenCarIsNotParked() {
        Transport truck = new Truck(2);
        Park park = new Park(Map.of(new Car(1), new Boolean[0], truck, new Boolean[3]));
        Parking car = new ParkingCar(new Car(1));
        boolean actual = car.parking(park);
        assertThat(actual).isFalse();
        assertThat(park.places().get(truck)[0]).isFalse();
        assertThat(park.places().get(truck)[1]).isFalse();
        assertThat(park.places().get(truck)[2]).isFalse();
    }
}
