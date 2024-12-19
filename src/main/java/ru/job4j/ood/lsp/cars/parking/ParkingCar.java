package ru.job4j.ood.lsp.cars.parking;
import ru.job4j.ood.lsp.cars.model.park.Park;
import ru.job4j.ood.lsp.cars.model.transport.Car;

public class ParkingCar implements Parking {

    private Car car;

    public ParkingCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean parking(Park park) {
        return false;
    }
}
