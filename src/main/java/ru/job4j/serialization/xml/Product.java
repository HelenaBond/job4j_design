package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.*;

import java.util.Arrays;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

    @XmlAttribute
    private boolean isFood;

    @XmlAttribute
    private int barcode;

    @XmlAttribute
    private double weightInKg;

    @XmlAttribute
    private String name;

    private DeliveryTerms conditions;

    @XmlElementWrapper(name = "ingredients")
    @XmlElement(name = "ingredient")
    private String[] ingredients;

    public Product() {
    }

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
