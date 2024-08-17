package ru.job4j.serialization.xml;

import java.util.Arrays;

public class Product {
    private boolean isFood;
    private int barcode;
    private double weightInKg;
    private String name;
    private DeliveryTerms conditions;
    private String[] ingredients;

    public Product(
            boolean isFood,
            int barcode,
            double weightInKg,
            String name,
            DeliveryTerms conditions,
            String[] ingredients) {
        this.isFood = isFood;
        this.barcode = barcode;
        this.weightInKg = weightInKg;
        this.name = name;
        this.conditions = conditions;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Product{"
                + "isFood=" + isFood
                + ", barcode=" + barcode
                + ", weightInKg=" + weightInKg
                + ", name='" + name + '\''
                + ", conditions=" + conditions
                + ", ingredients=" + Arrays.toString(ingredients) + '}';

    }
}
