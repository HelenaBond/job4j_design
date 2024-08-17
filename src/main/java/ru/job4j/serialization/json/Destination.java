package ru.job4j.serialization.json;

import java.util.Arrays;
import java.util.Objects;

public class Destination {
    private String phone;
    private int zipCode;
    private double distance;
    private boolean partner;
    private Address address;
    private String[] permissions;

    public Destination(String phone, int zipCode, double distance, boolean partner, Address address, String[] permissions) {
        this.phone = phone;
        this.zipCode = zipCode;
        this.distance = distance;
        this.partner = partner;
        this.address = address;
        this.permissions = permissions;
    }

    public String getPhone() {
        return phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public double getDistance() {
        return distance;
    }

    public boolean isPartner() {
        return partner;
    }

    public Address getAddress() {
        return address;
    }

    public String[] getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return "Destination{"
                + "phone='" + phone + '\''
                + ", zipCode=" + zipCode
                + ", distance=" + distance
                + ", isPartner=" + partner
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
        return zipCode == that.zipCode && Double.compare(distance, that.distance) == 0 && partner == that.partner && Objects.equals(phone, that.phone) && Objects.equals(address, that.address) && Arrays.equals(permissions, that.permissions);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(phone, zipCode, distance, partner, address);
        result = 31 * result + Arrays.hashCode(permissions);
        return result;
    }
}
