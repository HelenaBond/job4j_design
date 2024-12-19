package ru.job4j.ood.lsp.cars.model.transport;

import java.util.Objects;

public abstract class Transport {
    private final int size;

    public Transport(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Transport transport)) {
            return false;
        }
        return size == transport.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
