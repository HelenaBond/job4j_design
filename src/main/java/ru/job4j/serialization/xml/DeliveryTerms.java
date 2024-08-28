package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.XmlAttribute;

public class DeliveryTerms {

    @XmlAttribute
    private boolean isFreezing;

    @XmlAttribute
    private boolean dryEnvironment;

    public DeliveryTerms() {
    }

    public DeliveryTerms(boolean isFreezing, boolean dryEnvironment) {
        this.isFreezing = isFreezing;
        this.dryEnvironment = dryEnvironment;
    }

    @Override
    public String toString() {
        return "DeliveryTerms{"
                + "isFreezing=" + isFreezing
                + ", dryEnvironment=" + dryEnvironment + '}';
    }
}
