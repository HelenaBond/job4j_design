package ru.job4j.ood.lsp.cars.model.transport;

import java.util.Objects;

public class Car {
    private int size = 1;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Car car)) {
            return false;
        }
        return size == car.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
