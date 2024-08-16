package ru.job4j.serialization.json;

import java.util.Arrays;
import java.util.Objects;

public class Destination {
    private String phone;
    private int zipCode;
    private double distance;
    private boolean isPartner;
    private Address address;
    private String[] permissions;

    public Destination(String phone, int zipCode, double distance, boolean isPartner, Address address, String[] permissions) {
        this.phone = phone;
        this.zipCode = zipCode;
        this.distance = distance;
        this.isPartner = isPartner;
        this.address = address;
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Destination{"
                + "phone='" + phone + '\''
                + ", zipCode=" + zipCode
                + ", distance=" + distance
                + ", isPartner=" + isPartner
                + ", address=" + address
                + ", permissions=" + Arrays.toString(permissions)
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Destination that)) {
            return false;
        }
        return zipCode == that.zipCode && Double.compare(distance, that.distance) == 0 && isPartner == that.isPartner && Objects.equals(phone, that.phone) && Objects.equals(address, that.address) && Arrays.equals(permissions, that.permissions);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(phone, zipCode, distance, isPartner, address);
        result = 31 * result + Arrays.hashCode(permissions);
        return result;
    }
}
